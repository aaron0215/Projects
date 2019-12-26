SAMPLE_PROFILE_SETTINGS = java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
 -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=./sample_profile.jfr,settings=profile

SAMPLE_HEAP_STATS_SETTINGS = java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
 -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=./sample_heap_stats_profile.jfr,settings=heap_stats.jfc

MY_PROFILE_SETTINGS = java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
 -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=./my_profile.jfr,settings=profile

MY_HEAP_STATS_SETTINGS = java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
 -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=./my_heap_stats_profile.jfr,settings=heap_stats.jfc


junit5:
	javac -cp .:./junit-platform-console-standalone-1.3.2.jar *.java
	java -jar junit-platform-console-standalone-1.3.2.jar --class-path . -p ""


sample_profiler:
	javac SampleProfilerApplication.java
	$(SAMPLE_PROFILE_SETTINGS) SampleProfilerApplication 10000000
	$(SAMPLE_HEAP_STATS_SETTINGS) SampleProfilerApplication 10000000
	ls -al *.jfr

my_profiler:
	javac MyProfiler.java
	$(MY_PROFILE_SETTINGS) MyProfiler 10000000
	$(MY_HEAP_STATS_SETTINGS) MyProfiler 10000000
	@echo "jmc -open my_profile.jfr"
	@echo "jmc -open my_heap_stats_profile.jfr"


clean:
	\rm -f *.class
	\rm -f *.jfr
