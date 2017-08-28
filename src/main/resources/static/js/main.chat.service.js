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