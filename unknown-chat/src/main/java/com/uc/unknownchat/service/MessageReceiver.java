package com.uc.unknownchat.service;

public interface MessageReceiver {

	public String pullFromQueue(String queue);
}
