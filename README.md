# Experiment 8: Implement Menus and Image Album Using GridView in Android

## Aim

To develop an Android application using Kotlin and XML that displays an image album in a 3 × 3 grid layout, incorporating images from drawable resources, internal storage, and online URLs. The application also implements an options menu and a context popup menu for performing basic album operations.

## Objectives

- To implement an Options Menu using `PopupMenu`.
- To display images in a three-column `GridView`.
- To load images from drawable resources.
- To select images from the device's internal storage using the Android Photo Picker.
- To load images from URLs using the Coil image-loading library.
- To implement a popup menu for individual images using a long press.
- To provide basic album operations such as selecting, editing, deleting, and refreshing images.

## Software Requirements

- Android Studio
- Kotlin programming language
- XML for UI design
- Android SDK
- Android Emulator or Android smartphone
- Coil image-loading library
- Internet connection for loading online images

## Project Description

The application, titled **My Album**, displays nine image positions arranged in three rows and three columns. Each row represents a different image source.

1. **Drawable Images:** Three images stored in the application's `res/drawable` directory.
2. **Internal Storage Images:** Three images selected from the device using Android's Photo Picker.
3. **URL Images:** Three images loaded from online image URLs using the Coil library.

The application includes a top-right album options menu and an individual-image popup menu that appears when the user long-presses an image.

## Features

- Three-row image album with three columns per row.
- Separate headings for Drawable Images, Internal Storage Images, and URL Images.
- Image selection using the Android Photo Picker.
- Online image loading using Coil.
- Options menu with Add Photos from Storage, Select, Edit, Delete, Refresh Album, and Album Settings.
- Context popup menu with Select Image, Edit Image, Delete Image, and Image Details.
- Image labels removed from the bottom of the images for a clean interface.

## Project Structure

```text
MenuWebViewDemo/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/menuwebviewdemo/
│           │       ├── MainActivity.kt
│           │       └── AlbumAdapter.kt
│           ├── res/
│           │   ├── drawable/
│           │   │   ├── album_drawable1.jpeg
│           │   │   ├── album_drawable2.jpeg
│           │   │   └── album_drawable3.jpeg
│           │   ├── layout/
│           │   │   ├── activity_main.xml
│           │   │   └── item_album.xml
│           │   └── values/
│           └── AndroidManifest.xml
└── README.md
```

## Procedure

1. Create a new Android Studio project using the Empty Views Activity template.
2. Select Kotlin as the programming language.
3. Add three images to the `res/drawable` directory.
4. Create `activity_main.xml` with a title, an options menu button, three row headings, and three GridViews.
5. Create `item_album.xml` to display each image without a text label underneath.
6. Implement the `AlbumItem` data class and `AlbumAdapter` class to manage drawable, storage, and URL images.
7. Implement `MainActivity.kt` to initialize the three image rows and connect the adapters.
8. Use Android's Photo Picker to select up to three images from the device.
9. Add the Coil dependency to load images from online URLs.
10. Implement the album options menu and the individual-image popup menu.
11. Add Internet permission to `AndroidManifest.xml`.
12. Run the application on an emulator or Android smartphone and test the image-loading and menu functionality.

## Important Implementation Details

### 1. GridView

The `GridView` displays images in three columns. Three separate GridViews are used to maintain independent rows for the different image sources.

### 2. Drawable Images

Drawable images are stored in the application's resources and loaded using `setImageResource()`.

### 3. Internal Storage Images

The Android Photo Picker allows the user to select images from the device. The selected image URIs are assigned to the three positions in the internal storage row.

### 4. URL Images

The Coil library loads online images into ImageViews using their URLs. Internet permission is required for this functionality.

### 5. Options Menu

The top-right menu provides the following options:

- Add Photos from Storage
- Select
- Edit
- Delete
- Refresh Album
- Album Settings

### 6. Individual Image Popup Menu

Long-pressing an image opens a popup menu containing:

- Select Image
- Edit Image
- Delete Image
- Image Details

The current implementation supports removing and replacing storage images. Drawable and URL images remain fixed. The Edit option for a storage image opens the Photo Picker rather than a full image-editing interface.

## Test Cases

| Test Case ID | Test Scenario | Test Steps | Expected Result | Status |
|---|---|---|---|---|
| TC01 | Launch and display album | Open the application and observe the screen. | The album displays three rows with three images per row and appropriate headings. | Pass |
| TC02 | Load images from different sources | View drawable images, select photos from internal storage, and check online images. | Images from drawable resources, internal storage, and URLs are displayed correctly. | Pass |
| TC03 | Verify album options menu | Tap the three-dot button in the top-right corner. | The album options menu appears with the available options. | Pass |
| TC04 | Verify individual image popup menu | Long-press an image in the album. | The popup menu appears with Select Image, Edit Image, Delete Image, and Image Details. | Pass |


## Output

The application displays a three-row, three-column image album with the following arrangement:

```text
My Album                                  Options

Drawable Images
[Image 1]      [Image 2]      [Image 3]

Internal Storage Images
[Image 1]      [Image 2]      [Image 3]

URL Images
[Image 1]      [Image 2]      [Image 3]
```

The user can select images from internal storage, view online images, open the album options menu, and access a popup menu by long-pressing an individual image.

### Screenshot 1: Main Album Screen

Show the complete application screen with the My Album title, three-dot options button, all three row headings, and the three image rows.

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/2286c4a5-dfd2-4a77-852a-fe10b21e824a" />

### Screenshot 2: Images Selected from Internal Storage

Show the application after selecting three images using the Android Photo Picker.
<img width="1366" height="720" alt="Screenshot 2026-10-09 221843" src="https://github.com/user-attachments/assets/4f4f3cec-5409-48b7-bc31-09f6c432b573" />


### Screenshot 3: Album Options Menu

 Open the three-dot menu and capture the available album options.

<img width="1366" height="720" alt="Screenshot 2026-10-09 221843" src="https://github.com/user-attachments/assets/4f4f3cec-5409-48b7-bc31-09f6c432b573" />


### Screenshot 4: Individual Image Popup Menu

 Long-press one image and capture the popup menu showing Select Image, Edit Image, Delete Image, and Image Details.

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/24637282-a0e7-4a14-9ef0-c921fef8188e" />


### Screenshot 5: Online Images Loaded Successfully

Show the URL Images row after all three online images have loaded.

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/cc4f4874-368d-4e94-baad-0fbf535a5f6c" />


### Screenshot 6: Storage Image Deletion

Show the internal storage row after deleting one image, with its placeholder visible.

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/b5c91174-e253-43a0-9cc8-7bbaa3185a8c" />


## Result

The Android Image Album application was implemented using Kotlin and XML. The application demonstrates GridView-based image display, image loading from drawable resources, internal storage selection, and online URLs. Options and popup menus were added to provide basic album interactions. The test cases can be used to verify the application's image-loading and menu functionality.

