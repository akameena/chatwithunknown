package com.uc.unknownchat.service;

public interface MessageSender {

	public void pushInQueue(String msg,String queue);
}
