## config.xml

```xml
<!-- apk签名 ，加密后的值-->
<preference name="APP_SIGN_VALUE" value=""/>

<!-- apk签名 ，加密salt-->
<preference name="APP_SIGN_SALT" value="go-trade"/>

```

## 用法

```javascript

cordova.plugins.CordovaPluginSignatureCheck.checkSignature(function(data){
	// data = {pass: true/false}
    alert('验证签名是否通过： ' + JSON.stringify(data));
}, function(error){
    alert(error);
})
```