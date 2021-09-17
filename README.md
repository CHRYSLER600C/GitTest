# GitTest
test only for git

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	   implementation 'com.github.CHRYSLER600C.GitTest:mylibrary:v1.1.2'
	   implementation 'com.github.CHRYSLER600C.GitTest:contact:v1.1.2'
	}
 
 [![](https://jitpack.io/v/CHRYSLER600C/GitTest.svg)](https://jitpack.io/#CHRYSLER600C/GitTest)


FAQ

1 错误：fatal: unable to access 'https://github.com/xxx.git/': Unknown SSL protocol error in connection to github.com:443 
  解决：git config http.sslVerify "false"



Git常用命令

回退代码：
git reset --hard commit_id  
强制提交回退后的代码：
git push origin HEAD --force

Git打标签
在 Git 中列出已有的标签非常简单，只需要输入 git tag （可带上可选的 -l 选项 --list）
在 Git 中创建附注标签：
$ git tag -a v1.4 -m "my version 1.4"
推送标签到共享服务器上：
$ git push origin v1.5

