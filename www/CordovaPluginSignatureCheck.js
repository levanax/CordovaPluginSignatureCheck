var exec = require('cordova/exec');

exports.checkSignature = function (success, error) {
    exec(success, error, 'CordovaPluginSignatureCheck', 'checkSignature', []);
};
