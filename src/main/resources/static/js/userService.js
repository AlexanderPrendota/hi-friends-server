var currenActiveUser = 0;

jQuery(function ($) {

    getActiveUsers();

    var chatPerson = null;
    var name = null;
    var avatar = null;
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

    $(window).bind("beforeunload", function () {
        $.ajax({
            type: 'POST',
            async: false,
            url: 'api/user/logout/' + chatOwner.email,
            contentType: "application/json"
        });
    });

    function getActiveUsers() {
        getUsers();
        setInterval(function () {
            getUsers();
        }, 10000);
    }

    function getUsers() {
        $.ajax({
            type: "GET",
            url: '/api/user/active',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                if (response.length !== currenActiveUser) {
                    currenActiveUser = response.length;
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
            chatPerson = $(event.target).attr('id');
            $('#chat').show();
            console.log("ЖМЯК");
            loadChatMessages()
        });

        function loadChatMessages() {
            downloadMessages();
        }

        function downloadMessages() {
            console.log("Hello from message!")
            if (!isDownloading) {
                isDownloading = true;
                $("#messages").load("messages", {
                    ownerId: chatOwner.id,
                    userId: chatPerson
                });
                $('#messages').animate({scrollTop: $('#messages').prop("scrollHeight")}, 500);
            }
            isDownloading = false;
        }

        $('#send').click(function() {
            currentChatId = $("#msgTable").attr("data-chat-id");
            var msgText = $('#message').val();
            console.log(currentChatId);

            var msgData = JSON.stringify({
                recipientId: chatPerson,
                senderId: chatOwner.id,
                text: msgText,
                timeStamp: new Date(),
                chatId: 1
            });
            if (msgText.length > 0) {
                $.ajax({
                    type: "POST",
                    url: "api/message/save",
                    dataType: "json",
                    contentType: "application/json",
                    data: msgData,
                    success: function (response) {
                        console.log(response);
                        downloadMessages();
                        $("#message").val("");
                    }
                });
            }
        });
    }
});


