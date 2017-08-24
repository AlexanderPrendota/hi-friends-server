var currenActiveUser = 0;

jQuery(function ($) {

    getActiveUsers();

    var chatPerson = null;
    var name = null;
    var avatar = null;
    var lastChatPerson = null;
    var chatOwner = {
        id: $("#ownerTag").attr('data-owner-id'),
        name: $("#ownerTag").attr('data-owner-name'),
        email: $("#ownerTag").attr('data-owner-email'),
        imagePath: $("#ownerTag").attr('data-owner-avatar'),
        active: $("#ownerTag").attr('data-owner-active')
    };

    // do logout user after leaving page
    $(window).bind("beforeunload", function () {
        $.ajax({
            type: 'POST',
            async: false,
            url: 'api/user/logout/' + chatOwner.email,
            contentType: "application/json"
        });
    });


    // draw users table in dialog with icons
    function drawActiveUsers(data) {
        var ids = [];
        $('#users').empty();
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].id);
            var userId = data[i].id;
            name = data[i].name;
            imagePath = data[i].imagePath;
            var classActive = "_active";
            var classNot = "usersListIcon";
            var backImg = " src=" + '"' + imagePath + '"';
            var userRow = "<li class=" + classActive + "><img class=" + classNot + backImg + "/><span id=" + userId + ">" + name + "</span></li>"
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
            console.log(chatPerson);
            console.log("ЖМЯК");
            loadChatMessages()
        });
    }


    // polling active users
    function getActiveUsers() {
        getUsers();
        setInterval(function () {
            getUsers();
        }, 10000);
    }

    // getting active users from server
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
});



