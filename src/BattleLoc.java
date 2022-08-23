import java.util.Random;

public abstract class BattleLoc extends Location {
	protected Obstacle obstacle;
	protected String award;
	protected String name;

	BattleLoc(Player player, String name, Obstacle obstacle, String award) {
		super(player);
		this.obstacle = obstacle;
		this.name = name;
		this.award = award;
	}

	public boolean getLocation() {
		int obsCount = obstacle.count();
		System.out.println("�uan buradas�n�z : " + this.getName());
		System.out.println("Dikkatli ol! Burada " + obsCount + " tane " + obstacle.getName() + " ya��yor !");
		System.out.print("<S>ava� veya <K>a� :");
		String selCase = scan.nextLine();
		selCase = selCase.toUpperCase();
		if (selCase.equals("S")) {
			if (combat(obsCount)) {
				System.out.println(this.getName() + " b�lgesindeki t�m d��manlar� temizlediniz !");
				if (this.award.equals("Food") && !player.getInv().isFood()) {
					System.out.println(this.award + " Kazand�n�z! ");
					player.getInv().setFood(true);
				} else if (this.award.equals("Water") && !player.getInv().isWater()) {
					System.out.println(this.award + " Kazand�n�z! ");
					player.getInv().setWater(true);
				} else if (this.award.equals("Firewood") && !player.getInv().isFirewood()) {
					System.out.println(this.award + " Kazand�n�z! ");
					player.getInv().setFirewood(true);
				}
				return true;
			}
			
			if(player.getHealthy() <= 0) {
				System.out.println("�ld�n�z !");
				return false;
			}
		
		}
		return true;
	}

	public boolean combat(int obsCount) {
		for (int i = 0; i < obsCount; i++) {
			int defObsHealth = obstacle.getHealth();
			playerStats();
			enemyStats();
			while (player.getHealthy() > 0 && obstacle.getHealth() > 0) {
				System.out.print("<V>ur veya <K>a� :");
				String selCase = scan.nextLine();
				selCase = selCase.toUpperCase();

				if (selCase.equals("V")) {

					String[] plyr = {"Obstacle", "Player" };
					Random random = new Random();
					int index = random.nextInt(plyr.length);

					if (plyr[index].equals("Player")) {

						System.out.println("Siz vurdunuz !");
						obstacle.setHealth(obstacle.getHealth() - player.getTotalDamage());
						afterHit();
					}
					else if (plyr[index].equals("Obstacle")) {

						if (obstacle.getHealth() > 0) {
							System.out.println();
							System.out.println("Canavar size vurdu !");
							player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor()));
							afterHit();
						}

					}
				}

				else {

					return false;

				}

			}

			if (obstacle.getHealth() < player.getHealthy()) {
				System.out.println("D��man� yendiniz !");
				player.setMoney(player.getMoney() + obstacle.getAward());
				System.out.println("G�ncel Paran�z : " + player.getMoney());
				obstacle.setHealth(defObsHealth);
			} else {
				return false;
			}
			System.out.println("-------------------");
		}
		return true;
	}

	public void playerStats() {
		System.out.println("Oyuncu De�erleri\n--------------");
		System.out.println("Can:" + player.getHealthy());
		System.out.println("Hasar:" + player.getTotalDamage());
		System.out.println("Para:" + player.getMoney());
		if (player.getInv().getDamage() > 0) {
			System.out.println("Silah:" + player.getInv().getwName());
		}
		if (player.getInv().getArmor() > 0) {
			System.out.println("Z�rh:" + player.getInv().getaName());
		}
	}

	public void enemyStats() {
		System.out.println("\n" + obstacle.getName() + " Değerleri\n--------------");
		System.out.println("Can:" + obstacle.getHealth());
		System.out.println("Hasar:" + obstacle.getDamage());
		System.out.println("ödül:" + obstacle.getAward());
	}



	public void afterHit() {
		System.out.println("Oyuncu Canı:" + player.getHealthy());
		System.out.println(obstacle.getName() + " Canı:" + obstacle.getHealth());
		System.out.println();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAward() {
		return award;
	}

	public String setAward(String award) {
		return award;
	}


	public Obstacle setObstacle(Obstacle obstacle){

		return obstacle;
	}

	public Obstacle getObstacle(){

		return obstacle;
	}


}


