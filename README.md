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
	   com.github.CHRYSLER600C.GitTest:mylibrary:v1.1.2
	   com.github.CHRYSLER600C.GitTest:contact:v1.1.2
	}
 
 [![](https://jitpack.io/v/CHRYSLER600C/GitTest.svg)](https://jitpack.io/#CHRYSLER600C/GitTest)
 
#FAQ：
1 错误：fatal: unable to access 'https://github.com/xxx.git/': Unknown SSL protocol error in connection to github.com:443 
  解决：git config http.sslVerify "false"
