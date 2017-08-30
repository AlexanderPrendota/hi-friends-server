/**
 * Created by aleksandrprendota on 27.08.17.
 */

var startNotification = function (chatOwnerId) {
    getNotifications(chatOwnerId);
    setInterval(function () {
        console.log("Notification...");
        getNotifications(chatOwnerId);
        setNotification();
    }, 5000);
};

function getNotifications(chatOwnerId) {
    $.ajax({
        type: "GET",
        url: '/api/user/notify/owner/' + chatOwnerId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if (response.length > 0) {
                for (var i = 0; i < response.length; i++) {
                    var userId = response[i].id;
                    if (notificationIds.indexOf(userId) == -1) {
                        notificationIds.push(userId);
                    }
                }
            }
        }
    });
}

function deleteNotification(userId) {
    // hate indexOf!
    for (var i = 0; i < notificationIds.length; i++) {
        if (notificationIds[i] == userId) {
            notificationIds.slice(i, 1);
            $('#badle-' + userId).remove();
        }
    }
}

function setNotification() {
    for (var i = 0; i < notificationIds.length; i++) {
        var userId = notificationIds[i];
        if (!$('#badle-' + userId).length && chatPerson != userId) {
            var notify = "<span id='badle-" + userId + "' class='badge'>1</span>";
            $('#' + userId).append(notify);
        }
    }
}