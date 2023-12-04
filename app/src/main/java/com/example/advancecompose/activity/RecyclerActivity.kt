package com.example.advancecompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.advancecompose.compose.column.displayListData

class RecyclerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // scrollableColumnDemo()
            /*lazyColumnDemo {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }*/
            displayListData(selectedIem = {
                // Toast.makeText(this,it.name,Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.intent(this, it))
            })
        }
    }
}
