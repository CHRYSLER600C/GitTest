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
    	    implementation 'com.github.CHRYSLER600C:GitTest:v1.1.1'
	}
 
#FAQ：
1 错误：fatal: unable to access 'https://github.com/xxx.git/': Unknown SSL protocol error in connection to github.com:443 解决：git config http.sslVerify "false"
