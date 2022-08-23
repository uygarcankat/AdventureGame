public class ToolStore extends NormalLoc {

	ToolStore(Player player) {
		super(player, "Ma�aza");
	}

	public boolean getLocation() {
		System.out.println("Para : " + player.getMoney());
		System.out.println("1. Silahlar");
		System.out.println("2. Z�rhlar");
		System.out.println("3. çıkış");
		System.out.print("Seçiminiz : ");
		int selTool = scan.nextInt();
		int selItemID;
		switch (selTool) {
		case 1:
			selItemID = weaponMenu();
			buyWeapon(selItemID);
			break;
		case 2:
			selItemID = armorMenu();
			buyArmor(selItemID);
			break;
		default:
			break;
		}

		return true;
	}
	
	public int armorMenu() {
		System.out.println("1. Hafif \t <Para : 15 - Hasar : 1>");
		System.out.println("2. Orta \t <Para : 25 - Hasar : 3>");
		System.out.println("3. A��r \t <Para : 40 - Hasar : 5>");
		System.out.println("4. ��k��");
		System.out.print("Silah Se�iniz : ");
		int selArmorID = scan.nextInt();
		return selArmorID;
	}


	public int weaponMenu() {
		System.out.println("1. Tabanca\t<Para : 25 - Hasar : 2>");
		System.out.println("2. K�l��\t<Para : 35 - Hasar : 3>");
		System.out.println("3. T�fek\t<Para : 45 - Hasar : 7>");
		System.out.println("4. ��k��");
		System.out.print("Silah Se�iniz : ");
		int selWeaponID = scan.nextInt();
		return selWeaponID;
	}
	
	public void buyArmor(int itemID) {
		int avoid = 0, price = 0;
		String aName = null;
		switch (itemID) {
		case 1:
			avoid = 1;
			aName = "Hafif Z�rh";
			price = 15;
			break;
		case 2:
			avoid = 3;
			aName = "Orta Z�rh";
			price = 15;
			break;
		case 3:
			avoid = 5;
			aName = "A��r Z�rh";
			price = 40;
			break;
		case 4:
			System.out.println("��k�� Yap�l�yor.");
			break;
		default:
			System.out.println("Ge�ersiz ��lem !");
			break;
		}

		if (price > 0) {
			if (player.getMoney() >= price) {
				player.getInv().setArmor(player.getInv().getArmor()+avoid);
				player.getInv().setaName(aName);
				player.setMoney(player.getMoney() - price);
				System.out.println(aName + " sat�n ald�n�z, Engellenen Hasar : " + player.getInv().getArmor());
				System.out.println("Kalan Para :" + player.getMoney());
			} else {
				System.out.println("Para yetersiz. !");
			}
		}
	}



	public void buyWeapon(int itemID) {
		int damage = 0, price = 0;
		String wName = null;
		switch (itemID) {
		case 1:
			damage = 2;
			wName = "Tabanca";
			price = 25;
			break;
		case 2:
			damage = 3;
			wName = "K�l��";
			price = 35;
			break;
		case 3:
			damage = 7;
			wName = "T�fek";
			price = 45;
			break;
		case 4:
			System.out.println("��k�� Yap�l�yor.");
			break;
		default:
			System.out.println("Ge�ersiz ��lem !");
			break;
		}

		if (price > 0) {
			if (player.getMoney() > price) {
				player.getInv().setDamage(player.getInv().getDamage()+damage);
				player.getInv().setwName(wName);
				player.setMoney(player.getMoney() - price);
				System.out.println(wName + " sat�n ald�n�z, �nceki Hasar :" + player.getDamage() + ", Yeni Hasar : "
						+ player.getTotalDamage());
				System.out.println("Kalan Para :" + player.getMoney());
			} else {
				System.out.println("Para yetersiz. !");
			}
		}
	}

}
