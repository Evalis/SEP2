package server.database.infrastructure;

import shared.model.Manager;

public interface IManagerDatabase {

	public Manager logInManager(String email, String password);
}
