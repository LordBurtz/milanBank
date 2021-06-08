if [ ! -x /usr/bin/sqlite3 ] ; then
  echo "please install sqlite3 to continue"
fi
if [ ! -x /usr/bin/maven ] ; then
  echo "please install maven to continue"
fi
mkdir database
cd database || exit
sqlite3 database.db
echo "database setup compelete"
mkdir compile
cd compile || exit
git clone https://github.com/schoolerBurtz/milanBank.git
mvn package
cp target/milan-Bank*.jar ..
cd ..
rm -rf compile
echo "compile done"
java -jar milan-Bank*.jar
