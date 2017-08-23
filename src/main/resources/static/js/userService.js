
$( document ).ready(function() {
    getActiveUsers()
});


function getActiveUsers() {
    getUsers();
    setInterval(function() {
        getUsers();
    }, 10000);
}

function getUsers() {
    $.ajax({
        type: "GET",
        url : '/activeusers',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            //TODO: Draw in dialog
        }
    });
}

jQuery(function($) {
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
    console.log(chatOwner);
})