/**
 * Created by aleksandrprendota on 27.08.17.
 */
var currentActiveUser = 0;
var notificationIds = [];
var chatPerson = null;
var name = null;
var lastChatPerson = null;
var currentChatId;
var isDownloading = false;

jQuery(function ($) {

    var chatOwnerId = $("#ownerTag").attr('data-owner-id');

    // logout user after closing tab with chat
    $(window).bind("beforeunload", function () {
        $.ajax({
            type: 'POST',
            async: false,
            url: 'api/user/logout/' + chatOwnerId,
            contentType: "application/json"
        });
    });

    // for auto size textarea.
    autosize($('textarea'));

    chain(function(next) {
        // start notification
        startNotification(chatOwnerId);
        next();
    }).then(function(next) {
        // get all users who is online
        setTimeout(function() {
            getActiveUsers(chatOwnerId);
            next();
        }, 300);
    });

    //polling for getting chat message between two users
    loadMessage(chatOwnerId);

    // send message to companion. by 13 or click
    sendingMessage(chatOwnerId);

});

var getActiveUsers = function (chatOwnerId) {
    getUsers(chatOwnerId);
    setInterval(function () {
        getUsers(chatOwnerId);
    }, 10000);
};

var loadMessage = function (chatOwnerId) {
    setInterval(function () {
        if (chatPerson) {
            console.log("Monitoring...");
            downloadMessages(chatOwnerId);
        }
    }, 5000);
};

function getUsers(chatOwnerId) {
    $.ajax({
        type: "GET",
        url: '/api/user/active/' + chatOwnerId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if (response.length !== currentActiveUser) {
                currentActiveUser = response.length;
                drawActiveUsers(response, chatOwnerId);
            }
        }
    });
}

function drawActiveUsers(data, chatOwnerId) {
    var ids = [];
    var classActive = "_active";
    var classNot = "usersListIcon";
    $('#users').empty();
    for (var i = 0; i < data.length; i++) {
        var userId = data[i].id;
        ids.push(userId);
        var name = data[i].name;
        var imagePath = data[i].imagePath;
        var backImg = " src=" + '"' + imagePath + '"';
        var userRow = "<li class=" + classActive + "><img class=" + classNot + backImg + "/><span class='user-row' id=" + userId + ">" + name + "</span></li>";
        $('#users').append(userRow);

    }
    setNotification();

    if (chatPerson !== null){
        deleteNotification(chatPerson);
    }

    if (ids.indexOf(parseInt(chatPerson)) === -1) {
        if (chatPerson !== null) {
            lastChatPerson = parseInt(chatPerson);
            chatPerson = null;
        }
        if (ids.indexOf(lastChatPerson) !== -1) {
            chatPerson = lastChatPerson;
        }
    }

    $('#users span').on("click", function (event) {
        chatPerson = $(event.target).attr('id');
        deleteNotification(chatPerson);
        var apponentName = $('#' + chatPerson).text();
        $("#apponent").text(apponentName);
        $('#chat').show();
        downloadMessages(chatOwnerId);
    });
}

function downloadMessages(chatOwnerId) {
    $('#message').empty();
    console.log("Download for " + chatPerson);
    if (!isDownloading) {
        isDownloading = true;
        $("#messages").load("messages", {
            ownerId: chatOwnerId,
            userId: chatPerson
        });
        $('#messages').animate({
            scrollTop: $('#messages').prop("scrollHeight")
        }, 500);
    }
    isDownloading = false;
}

function sendingMessage(chatOwnerId) {
    $('#message').keypress(function (e) {
        if (e.which === 13) {
            $('#send').click();
        }
    });

    $('#send').click(function () {

        currentChatId = $("#msgTable").attr("data-chat-id");
        var msgText = $('#message').val();
        var msgData = JSON.stringify({
            senderId: chatOwnerId,
            text: msgText,
            timeStamp: new Date(),
            chatId: currentChatId
        });

        if (msgText.length > 0 && currentChatId > 0) {
            $.ajax({
                type: "POST",
                url: "api/message/save",
                contentType: "application/json",
                data: msgData,
                success: function (response) {
                    downloadMessages(chatOwnerId);
                    console.log("Отправлено успешно");
                    $("#message").val("");
                    $("textarea").css("height", "50px");
                },
                error: function (error) {
                    $("#message").val("");
                    console.log(error.responseText.message);
                }
            });
        }
    });
}

function chain(callback) {
    var queue = [];
    function _next() {
        var cb = queue.shift();
        if (cb) {
            cb(_next);
        }
    }
    setTimeout(_next, 0);
    var then = function(cb) {
        queue.push(cb);
        return { then: then }
    };
    return then(callback);
}