/**
 * Created by aleksandrprendota on 22.08.17.
 */
var currentUser;

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    currentUser = {
        email: profile.getEmail(),
        name: profile.getName(),
        avatar: profile.getImageUrl()
    };
    $("#email").val(currentUser.email);
    $("#name").val(currentUser.name);
    $("#avatar").val(currentUser.avatar);
    $("#registration-form").submit();
}

function onLoad() {
    gapi.load('auth2,signin2', function () {
        var auth2 = gapi.auth2.init();
        auth2.then(function () {
            gapi.signin2.render('google-signin-button', {
                'onsuccess': 'onSignIn'
            });
        });
    });
}