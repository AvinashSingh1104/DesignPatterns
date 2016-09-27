clear
sudo apt-get update
sudo apt-get install openjdk-7-jdk -y
sudo apt-get -u install ant -y
sudo apt-get install software-properties-common -y
sudo add-apt-repository ppa:couchdb/stable -y
sudo apt-get remove couchdb couchdb-bin couchdb-common -yf
sudo apt-get install couchdb -y

