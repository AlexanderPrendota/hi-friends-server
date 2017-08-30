/**
 * Created by aleksandrprendota on 28.08.17.
 */

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
        url: '/api/user/' + chatOwnerId + 'active/',
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

    if (chatPerson !== null) {
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
    setNotification();

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