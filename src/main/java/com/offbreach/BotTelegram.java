/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.offbreach;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author rafae
 */
public class BotTelegram extends AbilityBot {

    public static String BOT_TOKEN = "5479587580:AAFlIcVFqAD3nfKTGOHNZ8tKa7n03MCQGgQ";
    public static String BOT_USERNAME = "OFFBreachBot";

    private final String chatId;

    public BotTelegram(String chatId) {
        super(BOT_TOKEN, BOT_USERNAME);
        this.chatId = chatId;
    }

    @Override
    public long creatorId() {
        return 1641553345;
    }

}
