
[![Release](https://jitpack.io/v/ashLikun/LoadSwitch.svg)](https://jitpack.io/#ashLikun/LoadSwitch)

# **LoadSwitch**
    加载中，加载为空，加载失败，之间的切换
## 使用方法

build.gradle文件中添加:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
并且:

```gradle
dependencies {
    compile 'com.github.ashLikun:LoadSwitch:{latest version}'

    compile 'me.zhanghai.android.materialprogressbar:library:1.4.2'
    compile 'com.github.ashLikun.frame:flatbutton:2.0.6'
}
```

### 1.用法
    //创建LoadSwitchService
    loadSwitchService = LoadSwitchService.generate(getSwitchRoot(), new MyOnLoadLayoutListener(this, getOnLoadSwitchClick()));


     loadSwitchService = LoadSwitchService.generate(findViewById(R.id.switchRoot), new MyOnLoadLayoutListener(this, this));
           findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (currStatus == 0) {
                        //显示为空
                       loadSwitchService.showEmpty(new ContextData());
                       currStatus = 1;
                   } else if (currStatus == 1) {
                        //显示为加载中
                       loadSwitchService.showLoading(new ContextData());
                       currStatus = 2;
                   } else if (currStatus == 2) {
                        //显示原来内容
                       loadSwitchService.showContent();
                       currStatus = 3;
                   } else if (currStatus == 3) {
                        //显示错误
                       loadSwitchService.showRetry(new ContextData());
                       currStatus = 0;
                   }
               }
           });
### 混肴


