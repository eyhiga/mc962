#!/bin/bash

mean=0.1
inc=0.1

for i in {0..9}
do
	for j in {0..9}
	do
		mean_s="$i$j"
		if [ "$mean_s" != "00" ]; then
			echo 0.$mean_s
			echo -n 0.$mean_s"; " >> ~/sim/out
			java Main 10000 1.0 0.$mean_s > ~/sim/mean_$mean_s
			Rscript script.R -args ~/sim/mean_$mean_s
		fi        
    done
done

echo 1.00
echo -n 1.00"; " >> ~/sim/out
java Main 1000 1.0 1.0 > ~/sim/mean_100
Rscript script.R -args ~/sim/mean_100
