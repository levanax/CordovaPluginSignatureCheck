
# 用法

安装：

```cmd
cordova plugin add https://github.com/levanax/CordovaPluginSignatureCheck.git
```

1. 定义配置 config.xml

```xml
<!-- apk签名 ，加密salt-->
<preference name="APP_SIGN_SALT" value="go-trade-mobile"/>

```

2. 使用下面的命令查看 签名文件的 MD5

```cmd
keytool -list -v -keystore test.keystore
```

3. 打开下列网址   

[HmacSHA256 Site](http://tool.oschina.net/encrypt?type=2)  
使用 HmacSHA256 加密方式，将上面配置的 APP_SIGN_SALT 作为密钥，把第2步拿到的MD5进行加密

4. 将第3步得到加密后的值设置在 APP_SIGN_VALUE

```xml
<!-- apk签名 ，加密后的值-->
<preference name="APP_SIGN_VALUE" value=""/>

```

5. 在JavaScript中使用

```javascript

cordova.plugins.CordovaPluginSignatureCheck.checkSignature(function(data){
	// data = {pass: true/false}
    alert('验证签名是否通过： ' + JSON.stringify(data));
}, function(error){
    alert(error);
})
```

6. 新增配置 config.xml, 是否启用该插件, value = enabled/disabled

```xml
<preference name="APP_SIGN_CHECK_STATUS" value="disabled" />
```

