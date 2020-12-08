# Sticky Slide View

이 프로젝트는 Andorid Custome View 입니다.
간편하게 Sticky Slide를 구현하기 위한 라이브러리로 View안에 내용은 마음것 추가 변경하실 수 있습니다.

This project is an Android Custom View.
It is a library to easily implement Sticky Slide, and you can change the contents of the view.

![device-2020-12-07](./documents/device-2020-12-07.gif){: width="30%" height="30%"}

## How to set
### Gradle
1. Add the JitPack repository to your build file
    ```
    allprojects {
    	repositories {
    		...
    		maven { url 'https://jitpack.io' }
    	}
    }
    ```

2. Add the dependency
    ```
    dependencies {
	        implementation 'com.github.Yeh35:android_stickyslideview:1.0'
	}
    ```

### Maven
1. Add the JitPack repository to your build file
    ```
    <repositories>
    	<repository>
    	    <id>jitpack.io</id>
    	    <url>https://jitpack.io</url>
    	</repository>
    </repositories>
    ```
2. Add the dependency
    ```
    <dependency>
	    <groupId>com.github.Yeh35</groupId>
	    <artifactId>android_stickyslideview</artifactId>
	    <version>1.0</version>
	</dependency>
    ```

## How to use
XML에 추가 또는 코드로 추가 하는 방법이 있다.

* XML로 추가하는 방법 다음을 Actvitiy에 추가하면 된다.
    ```xml
    <com.yeh35.android.stickyslideview.StickySlideView
        android:id="@+id/sticky_slide_view_xml"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <View
            android:layout_width="match_parent"
            android:layout_height="500dp"/>

    </com.yeh35.android.stickyslideview.StickySlideView>
    ```

* 코드로 추가하는 방법
    ```kotlin
    val stickySlideViewCode = StickySlideView(this)
    stickySlideViewCode.addView(View(this), ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        5000
    ))
    this.addContentView(stickySlideViewCode, ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    ))
    ```