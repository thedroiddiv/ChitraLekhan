<h1 align="center">Chitra Lekhan</h1>

<p align="center">
  <img alt="API" src="https://img.shields.io/badge/Api%2021+-50f270?logo=android&logoColor=black&style=for-the-badge"/>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/>
</p>

<p align="center"><b>An Android library for image annotation built using Jetpack Compose and Canvas API</b></p>

## ✨ Features

- Freehand and shape-based drawing
- Undo/Redo/Clear support
- Configurable brush color and size
- Zoom and pan gestures
- Easy-to-use toolbar component
- Jetpack Compose integration

## 📦 Installation

### Kotlin (build.gradle.kts)

```kotlin
dependencies {
    implementation("com.github.karya-inc:chitralekhan:<latest_release>")
}
```

### Groovy (build.gradle)

```groovy
dependencies {
    implementation 'com.github.karya-inc:chitralekhan:<latest_release>'
}
```

## 🚀 Usage

### Step 1: Create an instance

```kotlin
val chitraLekhan = rememberChitraLekhan(
    image = bitmap,
    drawMode = DrawMode.FreeHand,
    color = colors.random(),
    width = 1f
)
```

### Step 2: Call the canvas composable

```kotlin
ChitraLekhanCanvas(
    chitraLekhan = chitraLekhan,
    modifier = Modifier.fillMaxSize()
)
```

### Step 3: Add optional toolbar

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
    onClear = chitraLekhan::clear,
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

## ⚙️ Available Configurations

| Configuration | Values |
|---------------|--------|
| **DrawMode** | `FreeHand` |
| | `Circle` |
| | `Polygon(val sides: Int)` |
| | `Rectangle` |
| | `None` (for pan/zoom only) |

## 📱 Sample App

Check out the [Sample App](https://github.com/karya-inc/ChitraLekhan/tree/master/app) for a working example.

<img src='https://github.com/user-attachments/assets/475e3b31-29df-4ef8-8869-a96f88bde645' width='256'/>

## 🙏 Acknowledgements

Inspired by:
- [DrawFull](https://github.com/thedroiddiv/DrawFull)
- Image Annotation Tool for Karya Android App
