var currentActiveUser = 0;
var currentMessage = 0;

jQuery(function ($) {
    autosize($('textarea'));
    var chatPerson = null;
    var name = null;
    var lastChatPerson = null;
    var currentChatId;
    var isDownloading = false;
    var chatOwner = {
        id: $("#ownerTag").attr('data-owner-id'),
        name: $("#ownerTag").attr('data-owner-name'),
        email: $("#ownerTag").attr('data-owner-email'),
        imagePath: $("#ownerTag").attr('data-owner-avatar'),
        active: $("#ownerTag").attr('data-owner-active')
    };

    // logout user after closing tab with chat
    $(window).bind("beforeunload", function () {
        $.ajax({
            type: 'POST',
            async: false,
            url: 'api/user/logout/' + chatOwner.id,
            contentType: "application/json"
        });
    });

    getActiveUsers();


    function getActiveUsers() {
        getUsers();
        setInterval(function () {
            getUsers();
        }, 10000);
    }

    function getUsers() {
        $.ajax({
            type: "GET",
            url: '/api/user/active/' + chatOwner.id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                if (response.length !== currentActiveUser) {
                    currentActiveUser = response.length;
                    drawActiveUsers(response);
                }
            }
        });
    }

    function drawActiveUsers(data) {
        var ids = [];
        var classActive = "_active";
        var classNot = "usersListIcon";
        $('#users').empty();
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].id);
            var name = data[i].name;
            var imagePath = data[i].imagePath;
            var userId = data[i].id;
            var backImg = " src=" + '"' + imagePath + '"';
            var userRow = "<li class=" + classActive + "><img class=" + classNot + backImg + "/><span id=" + userId + ">" + name + "</span></li>";
            $('#users').append(userRow)
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
            var chatPerson = $(event.target).attr('id');
            var apponentName = $('#' + chatPerson).text();
            $("#apponent").text(apponentName);
            $('#chat').show();
            downloadMessages();
        });
    }

    var loadMessage = function () {
        setInterval(function () {
            if (chatPerson) {
                console.log("Monitoring...");
                downloadMessages();
            }
        }, 5000);
    };

    loadMessage();

    function downloadMessages() {
        $('#message').empty();
        console.log("Download for " + chatPerson);
        if (!isDownloading) {
            isDownloading = true;
            $("#messages").load("messages", {
                ownerId: chatOwner.id,
                userId: chatPerson
            });
            $('#messages').animate({
                scrollTop: $('#messages').prop("scrollHeight")
            }, 500);
        }
        isDownloading = false;
    }

    $('#message').keypress(function (e) {
        if (e.which === 13) {
            $('#send').click();
        }
    });

    $('#send').click(function () {
        currentChatId = $("#msgTable").attr("data-chat-id");
        var msgText = $('#message').val();
        var msgData = JSON.stringify({
            senderId: chatOwner.id,
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
                    downloadMessages();
                    console.log("Отправлено успешно");
                    $("#message").val("");
                },
                error: function (error) {
                    $("#message").val("");
                    console.log(error.responseText.message);
                }
            });
        }
    });
});