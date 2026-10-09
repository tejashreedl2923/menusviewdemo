package com.example.menuwebviewdemo

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var drawableGrid: GridView
    private lateinit var storageGrid: GridView
    private lateinit var urlGrid: GridView

    private lateinit var drawableItems: MutableList<AlbumItem>
    private lateinit var storageItems: MutableList<AlbumItem>
    private lateinit var urlItems: MutableList<AlbumItem>

    private lateinit var drawableAdapter: AlbumAdapter
    private lateinit var storageAdapter: AlbumAdapter
    private lateinit var urlAdapter: AlbumAdapter

    private var selectedRow = -1
    private var selectedPosition = -1

    private val photoPicker = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->

        for (i in 0 until 3) {
            storageItems[i] = if (i < uris.size) {
                AlbumItem(
                    title = "img${i + 1}",
                    imageUri = uris[i]
                )
            } else {
                AlbumItem("img${i + 1}")
            }
        }

        storageAdapter.notifyDataSetChanged()
        showMessage("${uris.size.coerceAtMost(3)} storage images selected")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawableItems = mutableListOf(
            AlbumItem("img1", R.drawable.album_drawable1),
            AlbumItem("img2", R.drawable.album_drawable2),
            AlbumItem("img3", R.drawable.album_drawable3)
        )

        storageItems = mutableListOf(
            AlbumItem("img1"),
            AlbumItem("img2"),
            AlbumItem("img3")
        )

        urlItems = mutableListOf(
            AlbumItem(
                "img1",
                imageUrl = "https://picsum.photos/id/1011/300/300"
            ),
            AlbumItem(
                "img2",
                imageUrl = "https://picsum.photos/id/1015/300/300"
            ),
            AlbumItem(
                "img3",
                imageUrl = "https://picsum.photos/id/1025/300/300"
            )
        )

        drawableGrid = findViewById(R.id.gridDrawable)
        storageGrid = findViewById(R.id.gridStorage)
        urlGrid = findViewById(R.id.gridUrl)

        drawableAdapter = AlbumAdapter(this, drawableItems)
        storageAdapter = AlbumAdapter(this, storageItems)
        urlAdapter = AlbumAdapter(this, urlItems)

        drawableGrid.adapter = drawableAdapter
        storageGrid.adapter = storageAdapter
        urlGrid.adapter = urlAdapter

        setupImageLongPress(drawableGrid, 0)
        setupImageLongPress(storageGrid, 1)
        setupImageLongPress(urlGrid, 2)

        findViewById<ImageButton>(R.id.btnAlbumMenu)
            .setOnClickListener { view ->
                showAlbumMenu(view)
            }
    }

    private fun setupImageLongPress(grid: GridView, row: Int) {
        grid.setOnItemLongClickListener { _, view, position, _ ->
            selectedRow = row
            selectedPosition = position
            showImageMenu(view)
            true
        }
    }

    private fun showAlbumMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)

        popup.menu.add("Add Photos from Storage")
        popup.menu.add("Select")
        popup.menu.add("Edit")
        popup.menu.add("Delete")
        popup.menu.add("Refresh Album")
        popup.menu.add("Album Settings")

        popup.setOnMenuItemClickListener { item ->
            when (item.title.toString()) {
                "Add Photos from Storage" -> {
                    photoPicker.launch("image/*")
                    true
                }

                "Select" -> {
                    showMessage("Long-press an image to select it")
                    true
                }

                "Edit" -> {
                    if (selectedPosition >= 0) {
                        editSelectedImage()
                    } else {
                        showMessage("Long-press an image first")
                    }
                    true
                }

                "Delete" -> {
                    if (selectedPosition >= 0) {
                        deleteSelectedImage()
                    } else {
                        showMessage("Long-press an image first")
                    }
                    true
                }

                "Refresh Album" -> {
                    drawableAdapter.notifyDataSetChanged()
                    storageAdapter.notifyDataSetChanged()
                    urlAdapter.notifyDataSetChanged()
                    showMessage("Album refreshed")
                    true
                }

                "Album Settings" -> {
                    showMessage("Album Settings selected")
                    true
                }

                else -> false
            }
        }

        popup.show()
    }

    private fun showImageMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)

        popup.menu.add("Select Image")
        popup.menu.add("Edit Image")
        popup.menu.add("Delete Image")
        popup.menu.add("Image Details")

        popup.setOnMenuItemClickListener { item ->
            when (item.title.toString()) {
                "Select Image" -> {
                    showMessage("Image selected")
                    true
                }

                "Edit Image" -> {
                    editSelectedImage()
                    true
                }

                "Delete Image" -> {
                    deleteSelectedImage()
                    true
                }

                "Image Details" -> {
                    showMessage(
                        "Image ${selectedPosition + 1}, row ${selectedRow + 1}"
                    )
                    true
                }

                else -> false
            }
        }

        popup.show()
    }

    private fun editSelectedImage() {
        if (selectedRow == 1 && selectedPosition >= 0) {
            photoPicker.launch("image/*")
        } else {
            showMessage("Editing is available for storage images only")
        }
    }

    private fun deleteSelectedImage() {
        if (selectedRow == 1 && selectedPosition >= 0) {
            storageItems[selectedPosition] =
                AlbumItem("img${selectedPosition + 1}")
            storageAdapter.notifyDataSetChanged()
            selectedPosition = -1
            selectedRow = -1
            showMessage("Storage image removed")
        } else {
            showMessage("Only storage images can be deleted")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
