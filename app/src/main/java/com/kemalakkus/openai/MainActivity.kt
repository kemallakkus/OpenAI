package com.kemalakkus.openai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kemalakkus.openai.databinding.ActivityMainBinding
import com.kemalakkus.openai.model.Message

class MainActivity : AppCompatActivity() {


    private lateinit var _binding : ActivityMainBinding
    private lateinit var chatGptViewModel: ChatGptViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val binding = _binding.root
        setContentView(binding)

        chatGptViewModel = ViewModelProvider(this)[ChatGptViewModel::class.java]

        val llm = LinearLayoutManager(this)
        _binding.recyclerView.layoutManager = llm

        chatGptViewModel.messageList.observe(this){messages ->
            val adapter = MessageAdapter(messages)
            _binding.recyclerView.adapter = adapter
        }

        _binding.sendBtn.setOnClickListener {
            val question = _binding.messageEditText.text.toString()
            chatGptViewModel.addToChat(question, Message.SENT_BY_ME,chatGptViewModel.getCurrentTimestamp())
            _binding.messageEditText.setText("")
            chatGptViewModel.callApi(question)
        }


    }
}