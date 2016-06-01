echo "Modify this file first, to move generated files where you wish"
#mv generated/batch.txt generated/jammazwanBatch100.txt
rm -rf workspace
mv generated workspace
#tar -cvf jammazwan100.zip workspace
#mv workspace/jammazwanBatch100.txt ..
# broken up into sections in case you want to test smaller chunks
mv workspace/fa* ..
#mv workspace/ab* ..
#mv workspace/ac* ..
#mv workspace/ad* ..
#ls workspace
