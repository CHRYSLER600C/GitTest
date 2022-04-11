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
命令 git tag <tagname> 用于新建一个标签，默认为HEAD，也可以指定一个commit id；
命令 git tag -a <tagname> -m "this is memeo" 可以指定标签信息；
命令 git tag 可以查看所有标签。
命令 git log --pretty=oneline --abbrev-commit 找到历史提交的commit id
命令 git tag -d <tagname> 删除标签
命令 git push origin :refs/tags/<tagname> 可以删除一个远程标签。

重新设置地址：
git remote set-url origin http://gc.xxxx.cn:3000/xxxx/xxxx.git

从命令行创建一个新的仓库
git init
git add README.md
git commit -m "first commit"
git remote add origin http://xxxxxxx.git
git push -u origin master
