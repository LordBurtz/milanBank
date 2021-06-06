if [ ! -x /usr/bin/sqlite3 ] ; then
  echo "please install sqlite3 to continue"
fi
mkdir database
cd database
sqlite3 database.db