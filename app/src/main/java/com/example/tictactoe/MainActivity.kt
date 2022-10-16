package com.example.tictactoe

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TableLayout
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    enum class PlayingPlayer {
        First_Player,
        Second_Player

    }

    enum class WinnerOfGame {
        Player_One,
        Player_Two,
        No_One
    }

    var playingPlayer : PlayingPlayer? = null
    var winner : WinnerOfGame? = null

    var player1Options : ArrayList<Int> = ArrayList()
    var player2Options : ArrayList<Int> = ArrayList()
    var allDisabledImages : ArrayList<ImageButton?> = ArrayList()

    lateinit var imgButton1 : ImageButton
    lateinit var imgButton2 : ImageButton
    lateinit var imgButton3 : ImageButton
    lateinit var imgButton4 : ImageButton
    lateinit var imgButton5 : ImageButton
    lateinit var imgButton6 : ImageButton
    lateinit var imgButton7 : ImageButton
    lateinit var imgButton8 : ImageButton
    lateinit var imgButton9 : ImageButton

    lateinit var buttonSelected : ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playingPlayer = PlayingPlayer.First_Player
        imgButton1 = findViewById<ImageButton>(R.id.imgButton1)
        imgButton2 = findViewById<ImageButton>(R.id.imgButton2)
        imgButton3 = findViewById<ImageButton>(R.id.imgButton3)
        imgButton4 = findViewById<ImageButton>(R.id.imgButton4)
        imgButton5 = findViewById<ImageButton>(R.id.imgButton5)
        imgButton6 = findViewById<ImageButton>(R.id.imgButton6)
        imgButton7 = findViewById<ImageButton>(R.id.imgButton7)
        imgButton8 = findViewById<ImageButton>(R.id.imgButton8)
        imgButton9 = findViewById<ImageButton>(R.id.imgButton9)
    }

    fun imageButtonTapped (view: View) {
        var ranNum = (Math.random() * 9 + 1).toInt()

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        var selectedImageButton : ImageButton = view as ImageButton
        buttonSelected = view
        when (ranNum) {
            1 -> tableLayout.setBackgroundColor(Color.YELLOW)
            2 -> tableLayout.setBackgroundColor(Color.DKGRAY)
            3 -> tableLayout.setBackgroundColor(Color.GREEN)
            4 -> tableLayout.setBackgroundColor(Color.RED)
            5 -> tableLayout.setBackgroundColor(Color.LTGRAY)
            6 -> tableLayout.setBackgroundColor(Color.MAGENTA)
            7 -> tableLayout.setBackgroundColor(Color.CYAN)
            8 -> tableLayout.setBackgroundColor(Color.WHITE)
            9 -> tableLayout.setBackgroundColor(Color.BLUE)
        }

        var optionNum = 0

        when (selectedImageButton.id) {
            R.id.imgButton1 -> optionNum = 1
            R.id.imgButton2 -> optionNum = 2
            R.id.imgButton3 -> optionNum = 3
            R.id.imgButton4 -> optionNum = 4
            R.id.imgButton5 -> optionNum = 5
            R.id.imgButton6 -> optionNum = 6
            R.id.imgButton7 -> optionNum = 7
            R.id.imgButton8 -> optionNum = 8
            R.id.imgButton9 -> optionNum = 9
        }

        action(optionNum, selectedImageButton)
    }

    private fun action (optionNum : Int, selectedImageButton: ImageButton) {
        if (playingPlayer == PlayingPlayer.First_Player) {
            selectedImageButton.setImageResource(R.drawable.x)
            player1Options.add(optionNum)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            playingPlayer = PlayingPlayer.Second_Player
        }

        if (playingPlayer == PlayingPlayer.Second_Player) {
//            selectedImageButton.setImageResource(R.drawable.o)
//            player2Options.add(optionNum)
//            selectedImageButton.isEnabled = false
//            allDisabledImages.add(selectedImageButton)
//            playingPlayer = PlayingPlayer.First_Player

            var notSelectedImageNumbers = ArrayList<Int>()

            for (imageNumber in 1..9) {
                if(!(player1Options.contains(imageNumber))) {
                    if (!player2Options.contains(imageNumber)) {
                        notSelectedImageNumbers.add(imageNumber)
                    }
                }
            }

            try {
                var ranNum = ((Math.random() * notSelectedImageNumbers.size)).toInt()
                var imageNumber = notSelectedImageNumbers[ranNum]
                when (imageNumber) {
                    1 -> buttonSelected = imgButton1
                    2 -> buttonSelected = imgButton2
                    3 -> buttonSelected = imgButton3
                    4 -> buttonSelected = imgButton4
                    5 -> buttonSelected = imgButton5
                    6 -> buttonSelected = imgButton6
                    7 -> buttonSelected = imgButton7
                    8 -> buttonSelected = imgButton8
                    9 -> buttonSelected = imgButton9

                }
                buttonSelected.setImageResource(R.drawable.o)
                player2Options.add(imageNumber)
                buttonSelected.isEnabled = false
                allDisabledImages.add(selectedImageButton)
                playingPlayer = PlayingPlayer.First_Player
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

        specifyTheWinnerOfGame ()
    }

    private fun specifyTheWinnerOfGame () {
        if (player1Options.contains(1)
            && player1Options.contains(2)
            && player1Options.contains (3)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(1)
            && player2Options.contains(2)
            && player2Options.contains (3)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(1)
            && player1Options.contains(5)
            && player1Options.contains (9)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(1)
            && player2Options.contains(5)
            && player2Options.contains (9)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(1)
            && player1Options.contains(4)
            && player1Options.contains (7)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(1)
            && player2Options.contains(4)
            && player2Options.contains (7)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(3)
            && player1Options.contains(5)
            && player1Options.contains (7)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(3)
            && player2Options.contains(5)
            && player2Options.contains (7)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(3)
            && player1Options.contains(6)
            && player1Options.contains (9)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(3)
            && player2Options.contains(6)
            && player2Options.contains (9)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(2)
            && player1Options.contains(5)
            && player1Options.contains (8)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(2)
            && player2Options.contains(5)
            && player2Options.contains (8)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(4)
            && player1Options.contains(5)
            && player1Options.contains (6)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(4)
            && player2Options.contains(5)
            && player2Options.contains (6)) {
            winner = WinnerOfGame.Player_Two
        }
        else if (player1Options.contains(7)
            && player1Options.contains(8)
            && player1Options.contains (9)) {
            winner = WinnerOfGame.Player_One
        }
        else if (player2Options.contains(7)
            && player2Options.contains(8)
            && player2Options.contains (9)) {
            winner = WinnerOfGame.Player_Two
        }

        if (winner == WinnerOfGame.Player_One) {
            createAlert("Player One Wins", "Congratulations to Player 1",
                AlertDialog.BUTTON_POSITIVE, "OK", false)
            return
        }
        else if (winner == WinnerOfGame.Player_Two) {
            createAlert("Player Two Wins", "Congratulations to Player 2",
                AlertDialog.BUTTON_POSITIVE, "OK", false)
            return
        }
        checkDrawState()
    }

    private fun createAlert (title : String, message : String,
                             whichButton : Int, buttonText : String, cancelable : Boolean) {
        val alertDialog : AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setButton(whichButton, buttonText, {
                dialog: DialogInterface?, which: Int ->

            resetGame()
        })

        alertDialog.setCancelable(cancelable)
        alertDialog.show()
    }

    private fun resetGame () {
        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winner = WinnerOfGame.No_One

        imgButton1.setImageResource(R.drawable.xo)
        imgButton2.setImageResource(R.drawable.xo)
        imgButton3.setImageResource(R.drawable.xo)
        imgButton4.setImageResource(R.drawable.xo)
        imgButton5.setImageResource(R.drawable.xo)
        imgButton6.setImageResource(R.drawable.xo)
        imgButton7.setImageResource(R.drawable.xo)
        imgButton8.setImageResource(R.drawable.xo)
        imgButton9.setImageResource(R.drawable.xo)

        imgButton1.isEnabled = true
        imgButton2.isEnabled = true
        imgButton3.isEnabled = true
        imgButton4.isEnabled = true
        imgButton5.isEnabled = true
        imgButton6.isEnabled = true
        imgButton7.isEnabled = true
        imgButton8.isEnabled = true
        imgButton9.isEnabled = true
    }

    private fun checkDrawState () {
        if (allDisabledImages.size == 9 && winner != WinnerOfGame.Player_One
            && winner != WinnerOfGame.Player_Two) {

            createAlert("DRAW!!!", "No one won the game.",
                AlertDialog.BUTTON_POSITIVE, "OK", false)
        }
    }
}