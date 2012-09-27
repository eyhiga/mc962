#!/bin/bash

mean=0.1
inc=0.1

mkdir -p ./result

for i in {0..9}
do
	for j in {0..9}
	do
		mean_s="$i$j"
		if [ "$mean_s" != "00" ]; then
			echo 0.$mean_s
			echo -n 0.$mean_s"; " >> ./result/out
			java Main 10000 1.0 0.$mean_s > ./result/mean_$mean_s
			Rscript script.R -args ./result/mean_$mean_s
		fi        
    done
done

echo 1.00
echo -n 1.00"; " >> ./result/out
java Main 1000 1.0 1.0 > ./sim/mean_100
Rscript script.R -args ./result/mean_100
