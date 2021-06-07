if [ ! -x /usr/bin/sqlite3 ] ; then
  echo "please install sqlite3 to continue"
fi
mkdir database
cd database || exit
sqlite3 database.db