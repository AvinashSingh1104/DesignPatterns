clear
echo "Installing Java"
sudo apt-get update
sudo apt-get install openjdk-7-jdk -y
sudo apt-get install -y mongodb-org-server mongodb-org-shell mongodb-org-tools -y
sudo rm /var/lib/dpkg/lock
echo "Installing MongoDb"
sudo apt-get install mongodb -y
sudo service mongodb start 
sudo apt-get install mongodb-clients -y
sudo apt-get -u install ant -y

