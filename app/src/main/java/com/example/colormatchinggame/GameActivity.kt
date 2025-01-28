package com.example.colormatchinggame

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.colormatchinggame.R.drawable.*

class GameActivity : AppCompatActivity() {

    lateinit var frontAnim: AnimatorSet
    lateinit var backAnim: AnimatorSet
    private var isFront = true
    private var flippedCards = mutableListOf<Pair<Int, Int>>() // To track flipped cards (index and image)
    private var matchedPairs = 0 // To track completed pairs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val images: MutableList<Int> = mutableListOf(
            red, orange, yellow, green, cyan, blue, purple, pink,
            red, orange, yellow, green, cyan, blue, purple, pink
        )

        images.shuffle()

        // Cards and corresponding back sides
        val cards = arrayOf(
            findViewById<ImageView>(R.id.card11),
            findViewById<ImageView>(R.id.card12),
            findViewById<ImageView>(R.id.card13),
            findViewById<ImageView>(R.id.card14),
            findViewById<ImageView>(R.id.card21),
            findViewById<ImageView>(R.id.card22),
            findViewById<ImageView>(R.id.card23),
            findViewById<ImageView>(R.id.card24),
            findViewById<ImageView>(R.id.card31),
            findViewById<ImageView>(R.id.card32),
            findViewById<ImageView>(R.id.card33),
            findViewById<ImageView>(R.id.card34),
            findViewById<ImageView>(R.id.card41),
            findViewById<ImageView>(R.id.card42),
            findViewById<ImageView>(R.id.card43),
            findViewById<ImageView>(R.id.card44)
        )

        val scale = applicationContext.resources.displayMetrics.density
        cards.forEach { it.cameraDistance = 8000 * scale }

        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        // Function to flip cards with animation
        fun flipCard(card: ImageView, image: Int, isFront: Boolean) {
            if (isFront) {
                frontAnim.setTarget(card)
                backAnim.setTarget(card)
                frontAnim.start()
                backAnim.start()
                card.setImageResource(image) // Show the card image
            } else {
                frontAnim.setTarget(card)
                backAnim.setTarget(card)
                frontAnim.start()
                backAnim.start()
                card.setImageResource(R.drawable.card) // Reset to card back
            }
        }

        // Set click listeners for each card
        for (i in cards.indices) {
            cards[i].setOnClickListener {
                // Prevent interaction if the card is already flipped or matched
                if (flippedCards.any { pair -> pair.first == i } || flippedCards.size == 2) return@setOnClickListener

                flipCard(cards[i], images[i], isFront = true)
                flippedCards.add(Pair(i, images[i]))

                // Check if two cards are flipped
                if (flippedCards.size == 2) {
                    val firstCard = flippedCards[0]
                    val secondCard = flippedCards[1]

                    // Match logic
                    if (firstCard.second == secondCard.second) {
                        // Match found
                        matchedPairs++
                        flippedCards.clear()

                        // Check if all pairs are matched
                        if (matchedPairs == images.size / 2) {
                            Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        // No match, flip back after delay
                        cards[firstCard.first].postDelayed({
                            flipCard(cards[firstCard.first], R.drawable.card, isFront = false)
                            flipCard(cards[secondCard.first], R.drawable.card, isFront = false)
                            flippedCards.clear()
                        }, 1000)
                    }
                }
            }
        }
    }
}
