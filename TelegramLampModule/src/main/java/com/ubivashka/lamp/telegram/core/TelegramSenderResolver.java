package com.ubivashka.lamp.telegram.core;

import org.jetbrains.annotations.NotNull;

import com.pengrad.telegrambot.model.User;
import com.ubivashka.lamp.telegram.TelegramActor;
import com.ubivashka.lamp.telegram.dispatch.DispatchSource;

import revxrsal.commands.command.CommandActor;
import revxrsal.commands.command.ExecutableCommand;
import revxrsal.commands.process.SenderResolver;

public enum TelegramSenderResolver implements SenderResolver {
	INSTANCE;

	@Override
	public boolean isCustomType(Class<?> type) {
		return User.class.isAssignableFrom(type) || DispatchSource.class.isAssignableFrom(type);
	}

	@Override
	public @NotNull Object getSender(@NotNull Class<?> customSenderType, @NotNull CommandActor actor,
			@NotNull ExecutableCommand command) {
		TelegramActor telegramActor = (TelegramActor) actor;
		if (User.class.isAssignableFrom(customSenderType))
			return telegramActor.getUser();
		if (DispatchSource.class.isAssignableFrom(customSenderType))
			return telegramActor.getDispatchSource();
		return telegramActor;
	}

}
