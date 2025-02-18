
<div align="center">  
<img src='https://github.com/karya-inc/RawAudioRecorder/assets/69595691/1d70ff80-7639-4ab7-8fd4-3da69d95ca4e' width='256px' />  
</div>  
<h4>TODO: Create and add Logo</h4>
  
<h1 align="center">Chitra Lekhan</h1>  
  
</br>  
  
<p align="center">  
  <img alt="API" src="https://img.shields.io/badge/Api%2021+-50f270?logo=android&logoColor=black&style=for-the-badge"/></a>  
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/></a>  
<p/>  
  
<p align="center">An Android library for image annotation</p>  
  
# Gradle  
  
Kotlin: build.gradle.kts  
```kotlin  
dependencies {  
 implementation("com.github.karya-inc:chitralekhan:<latest_release>")
}  
```  
  
Groovy: build.gradle  
```kotlin  
dependencies {  
 implementation 'com.github.karya-inc:chitralekhan:<latest_release>'
}  
```  
  
# Usage  
  
Create an instance of ChitraLekhan  
```kotlin  
val chitraLekhan = rememberChitraLekhan(  
    image = bitmap,  
    drawMode = DrawMode.FreeHand,  
    color = colors.random(),  
    width = 1f  
) 
```  
 
 Call the `ChitraLekhanCanvas` composable.
```kotlin  
ChitraLekhanCanvas(  
    chitraLekhan = chitraLekhan,  
    modifier = Modifier.fillMaxSize()  
)
```  
  
You can add a toolbar to change the properties of drawing canvas. 
```kotlin  
var isColorPickerVisible by remember { mutableStateOf(false) }
ChitraLekhanToolbar(  
    colors = colors,  
    pickedColor = chitraLekhan.strokeColor.value,  
    isColorPickerVisible = isColorPickerVisible,  
    onColorPickerClicked = { isColorPickerVisible = !isColorPickerVisible },  
    onColorPicked = {  
    chitraLekhan.setColor(it)  
        isColorPickerVisible = false  
    },  
    drawMode = chitraLekhan.drawMode.value,  
    onDrawModeSelected = chitraLekhan::setDrawMode,  
    onClear = chitraLekhan::clear,  =
    onUndo = chitraLekhan::undo,  
    onRedo = chitraLekhan::redo,  
    modifier = Modifier  
        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))  
        .background(MaterialTheme.colorScheme.surfaceContainer)  
        .padding(8.dp),  
    brushSize = chitraLekhan.strokeWidth.value,  
    onBrushSizeChange = chitraLekhan::setWidth  
)  
```  
  
### Available Configurations  
  
| Configuration | Available values                                                                 |  
|---------------|----------------------------------------------------------------------------------|  
| DrawMode      | `FreeHand`                                                           |  
|               | `Circle`                                                             |  
|               | `Polygon(val sides: Int)`                                            |  
|               | `Rectangle`                                                          |               
|               | `None` : Do not draw anything on drag/pinch. This state is utilized for panning and zooming                                                                  |                                                                           |  

  
# Sample App  
Checkout the sample [App](https://github.com/karya-inc/ChitraLekhan/tree/master/app) for reference  
  
<img src='https://github.com/user-attachments/assets/475e3b31-29df-4ef8-8869-a96f88bde645' width='256'/>  

 <hr>
 
 This library is inspired by [DrawFull](https://github.com/thedroiddiv/DrawFull) and Image Annotation Tool for Karya Android App
