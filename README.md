
# formatted-text  
  
![Publish Artifact](https://github.com/grandcentrix/grandcentrix-formatted-text/workflows/Publish%20Artifact/badge.svg)  
  
This Library introduce a FormattingText and a FallbackValue to use language independent texts in the application view models.   
  
## FormattedText & FallbackValue
  
### ViewModel  
  
```kotlin  
class MyAwesomeViewModel {  
    var formattedTextWith = 32.formatWithFallback(R.string.formatted_text, R.string.lorem_ipsum)  
    val fallbackText =  
        (null as Int).formatWithFallback(R.string.formatted_text, R.string.lorem_ipsum)  
  
    val fallbackValue = "a string".withFallback(R.string.lorem_ipsum)  
    val nullWithFallbackValue = (null as Int).withFallback(R.string.lorem_ipsum)  
}
```  
  
### Fragment  
  
```kotlin  
 Hier kommt das Fragment zeugs hin
 ```  
  
### Databinding  
 
## Usage  
The library is available on GitHub Packages.  
  
In your project main `build.gradle.kts` you need:  
  
```  
repositories {  
 maven { url = uri("https://maven.pkg.github.com/grandcentrix/grandcentrix-formatted-text") }}  
```  
  
And in your module `build.gradle.kts`:  
  
```  
implementation("net.grandcentrix.formatted_texteither:android:1.0")  
```  
  
For the newest version please always check the [Packages](https://github.com/grandcentrix/grandcentrix-kotlin-either/packages/596752) page.