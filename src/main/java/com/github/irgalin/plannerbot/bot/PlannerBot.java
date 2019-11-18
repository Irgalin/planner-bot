package com.github.irgalin.plannerbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.github.irgalin.plannerbot.model.User;
import com.github.irgalin.plannerbot.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@PropertySource("classpath:telegram.properties")
@Slf4j
public class PlannerBot extends TelegramLongPollingBot {

   /* public PlannerBot(DefaultBotOptions options) {
        super(options);
    }*/

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() && !update.getMessage().hasText()) {
            return;
        }
        final long chatId = update.getMessage().getChatId();
        final org.telegram.telegrambots.meta.api.objects.User user = update.getMessage().getFrom();
        userRepository.save(new User(user.getId(), user.getFirstName()));
        SendMessage message = new SendMessage().setChatId(chatId).setText("Hi! " + user.getFirstName());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
