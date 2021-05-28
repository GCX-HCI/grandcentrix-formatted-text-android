  
# FormattedText & FallbackValue  
  ![Publish Artifact](https://github.com/GCX-HCI/grandcentrix-formatted-text-android/workflows/Publish%20Artifact/badge.svg)    
    
This library introduces a FormattingText and a FallbackValue to use language independent texts in the application view models.     
    
## Example  
 
### View model

```kotlin
class MyAwesomeViewModel {    
    private val nullInt: Int? = null    
    
    val formattedNumber = 16.format(R.string.formatted_number)
    val formattedNumberWithFallback = 32.format(R.string.formatted_number, R.string.fallback)    
    val formattedNullNumberWithFallback = nullInt.format(R.string.formatted_number, R.string.fallback)    
    val valueWithFallback = 32.textIfNull(R.string.fallback)
    val nullValueWithFallback = textIfNull.ifNull(R.string.fallback) }  
``` 

### View binding

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {    
    super.onCreate(savedInstanceState)    
    binding = ActivityViewBindingBinding.inflate(layoutInflater)    
    setContentView(binding.root)    
    
    binding.formattedNumber.text = viewModel.formattedNumber.resolveString(this)binding.formattedNumberWithFallback.text = viewModel.formattedNumberWithFallback.resolveString(this)    
    binding.formattedNullNumberWithFallback.text = viewModel.formattedNullNumberWithFallback.resolveString(this)    
    binding.valueWithFallback.text = viewModel.valueWithFallback.resolveString(this)
    binding.nullValueWithFallback.text = viewModel.nullValueWithFallback.resolveString(this)
}
```

### Data binding

#### Activity

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {    
    super.onCreate(savedInstanceState)    
    binding = ActivityDataBindingBinding.inflate(layoutInflater)    
    setContentView(binding.root)    
    binding.viewModel = viewModel 
}
```

#### Layout

```XML     
<?xml version="1.0" encoding="utf-8"?>
<layout>        
    <data>    
        <variable
            name="viewModel"
            type="net.grandcentrix.android.formatted_text.sample.MyAwesomeViewModel" />
	</data>    
    
    <LinearLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"  
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
        
        <TextView
            android:id="@+id/formatted_number"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.formattedNumber}" />
        <TextView
            android:id="@+id/formatted_number_with_fallback"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.formattedNumberWithFallback}" />
        <TextView
            android:id="@+id/formatted_null_number_with_fallback"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.formattedNullNumberWithFallback}" />
        <TextView
            android:id="@+id/value_with_fallback"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.valueWithFallback}" />
        <TextView
            android:id="@+id/null_value_with_fallback"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.nullValueWithFallback}" />
    </LinearLayout>
</layout>  
```

## Usage

The library is available on GitHub Packages.    
    
In your project main `build.gradle` you need:    
```
maven {
    url 'https://maven.pkg.github.com/GCX-HCI/grandcentrix-formatted-text-android/'
    credentials {
        username = project.findProperty("github.user") ?: System.getenv("GITHUB_ACTOR")
        password = project.findProperty("github.token") ?: System.getenv("GITHUB_TOKEN")
    }
}
```
Note: Add your github user and token to your `~/.gradle/gradle.properties` file.    

And in your module `build.gradle`:    
    
```
implementation("net.grandcentrix.formatted-text:android:1.0.0")
```    

 For the newest version please always check the [Packages](https://github.com/GCX-HCI/grandcentrix-formatted-text-android/packages) page.