package com.moin.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
     var PLAYER     = true
     var TURN_COUNT = 0

    lateinit var board : Array<Array<Button>>
    var boardStatus = Array(3){ IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(btnone,btntwo,btnthree),
            arrayOf(btnfour,btnfive,btnsix),
            arrayOf(btnseven,btneight,btnnine)
        )

        for (i in board){
            for (button in i){
                    button.setOnClickListener (this)
            }
        }

        initializeBoardStatus()
       btnReset.setOnClickListener {
           PLAYER = true
           TURN_COUNT = 0
           initializeBoardStatus()

       }

    }

    private fun initializeBoardStatus(){
        for (i in 0..2){
            for(j in 0..2){
                   boardStatus[i][j] =-1
            }
        }


        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""

            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnone -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.btntwo -> {
                updateValue(row = 0, col = 1, player = PLAYER)

            }
            R.id.btnthree -> {
                updateValue(row = 0, col = 2, player = PLAYER)

            }
            R.id.btnfour -> {
                updateValue(row = 1, col = 0, player = PLAYER)

            }
            R.id.btnfive -> {
                updateValue(row = 1, col = 1, player = PLAYER)

            }
            R.id.btnsix -> {
                updateValue(row = 1, col = 2, player = PLAYER)

            }
            R.id.btnseven -> {
                updateValue(row = 2, col = 0, player = PLAYER)

            }
            R.id.btneight -> {
                updateValue(row = 2, col = 1, player = PLAYER)

            }
            R.id.btnnine -> {
                updateValue(row = 2, col = 2, player = PLAYER)

            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if (PLAYER){
            updateDisplay("Player X turn")
        } else {
            updateDisplay("Player O turn")
        }

        if (TURN_COUNT ==9){
            updateDisplay("Game Draw")
        }

         checkwinner()


    }

    private fun checkwinner() {
        //hoeizontal rows
        for (i in 0..2){
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if (boardStatus[i][0] == 1){
                    updateDisplay("X won the game")
                    break
                } else if(boardStatus[i][0] == 0){
                    updateDisplay("O won the game")
                    break
                }
            }
        }


        //Vertical Columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("X won the game")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("O won the game")
                    break
                }
            }
        }

        //First Diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if (boardStatus[0][0] == 1) {
                updateDisplay("X won the game")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("O won the game")
            }
        }

        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if (boardStatus[0][2] == 1) {
                updateDisplay("X won the game")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("O won the game")
            }
        }
    }

    private fun updateDisplay(text: String) {
        playerName.text = text
        if (text.contains("won")){
            disableButton()
        }
    }

    private fun disableButton() {
        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }

    fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value

    }
    //Main class ends here
 }

