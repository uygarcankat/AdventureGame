import java.util.Random;

public abstract class BattleLocMine extends Location{

    protected Obstacle obstacle;
    protected String name;
    protected ToolStore toolStore;



    public BattleLocMine(Player player,String name,Obstacle obstacle){

        super(player);
        this.obstacle=obstacle;
        this.name=name;

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

            }

            if(player.getHealthy() <= 0) {
                System.out.println("�ld�n�z !");
                return false;
            }

            return true;
        }
        return true;
    }

    public boolean combat(int obsCount) {
        for (int i = 1; i < obsCount+1; i++) {
            int defObsHealth = obstacle.getHealth();
            obstacle.setDamage(obstacle.RandomDamage());
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
                        if(obstacle.getHealth()<0){
                            obstacle.setHealth(0);
                        }
                        afterHit();
                    }
                    else if (plyr[index].equals("Obstacle")) {

                        if (obstacle.getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor()));
                            if(player.getHealthy()<0){
                                player.setHealthy(0);
                            }
                            afterHit();
                        }

                    }
                }

                else {

                    return false;

                }

            }

            if (obstacle.getHealth() < player.getHealthy()) {
                System.out.println("Düşmanı yendiniz !");
                randomAward();
                System.out.println(i + " ' inci snake'den " + randomAward());
                obstacle.setHealth(defObsHealth);
            } else {
                return false;
            }
            System.out.println("-------------------");
        }
        return true;
    }



    public void playerStats() {
        System.out.println("Oyuncu Değerleri\n--------------");
        System.out.println("Can:" + player.getHealthy());
        System.out.println("Hasar:" + player.getTotalDamage());
        System.out.println("Para:" + player.getMoney());
        if (player.getInv().getDamage() > 0) {
            System.out.println("Silah:" + player.getInv().getwName());
            System.out.println("Envanter Damage : " + player.getInv().getDamage());
        }
        else if(player.getInv().getDamage() == 0){

            System.out.println("Silah: Yumruk");
            System.out.println("Envanter Damage : " + player.getInv().getDamage());


        }
        if (player.getInv().getArmor() > 0) {
            System.out.println("Zırh:" + player.getInv().getaName());
        }

        else{

            System.out.println("Zırh: Zırh yok");
        }
    }


    public void enemyStats() {
        System.out.println("\n" + obstacle.getName() + " Değerleri\n--------------");
        System.out.println("Can:" + obstacle.getHealth());
        System.out.println("Hasar:" + obstacle.getDamage());

    }


    public String randomAward() {


        int probability = (int)(Math.random()*100);

        if (probability <15) {
            int probability1 = (int) (Math.random() * 100);

            if (probability1 < 20) {

                player.getInv().setDamage(7 + player.getInv().getDamage());
                player.getInv().setwName("Tüfek");

                return "Tüfek kazandınız ";

            } else if (probability1 > 20 && probability1 < 50) {

                player.getInv().setDamage(3 + player.getInv().getDamage());
                player.getInv().setwName("kılıç");

                return "kılıç kazandınız ";

            } else {

                player.getInv().setDamage(2 + player.getInv().getDamage());
                player.getInv().setwName("Tabanca");
                return "Tabanca kazandınız ";

            }
        }

        else if(probability  >15 && probability <30){

            int probability1 = (int)(Math.random()*100);

            if (probability1 < 20){

                player.getInv().setArmor(5 + player.getInv().getArmor());
                player.getInv().setaName("Ağır Zırh");

                return "Ağır zırh kazandınız ";

            }

            else if(probability1 > 20 && probability1 < 50){

                player.getInv().setArmor(3 + player.getInv().getArmor());
                player.getInv().setaName("Orta Zırh");

                return "Orta zırh kazandınız";



            }

            else{

                player.getInv().setArmor(1 + player.getInv().getArmor());
                player.getInv().setaName("Hafif Zırh");

                return "Hafif zırh kazandınız ";
            }

        }

        else if(probability >30 && probability <55){

            int probability1 = (int)(Math.random()*100);

            if (probability1 < 20){

                player.setMoney(player.getMoney()+10);

                return "10 Para kazandınız ";

            }

            else if(probability1  > 20 && probability1 < 50){

                player.setMoney(player.getMoney()+5);
                return " 5 Para kazandınız ";}


                else{

                    player.setMoney(player.getMoney()+1);

                    return "1 Para kazandınız ";
                }
            }


        else{

            return "Null item ";
        }
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

    public ToolStore setToolStore(ToolStore toolStore){

        return toolStore;
    }

    public ToolStore getToolStore(){

        return toolStore;
    }
















    public Obstacle setObstacle(Obstacle obstacle){

        return obstacle;
    }

    public Obstacle getObstacle(){

        return obstacle;
    }


}















