package com.certcare.pproject.repository;
import com.certcare.pproject.domain.ChatBot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatBotRepository extends JpaRepository<ChatBot, Long> {
    List<ChatBot> findAllByOrderByIdDesc();
}
