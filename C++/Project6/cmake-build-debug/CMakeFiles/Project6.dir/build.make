# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.9

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2017.3.2\bin\cmake\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2017.3.2\bin\cmake\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = D:\CS120\Project6

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = D:\CS120\Project6\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Project6.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Project6.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Project6.dir/flags.make

CMakeFiles/Project6.dir/main.cpp.obj: CMakeFiles/Project6.dir/flags.make
CMakeFiles/Project6.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\CS120\Project6\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Project6.dir/main.cpp.obj"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Project6.dir\main.cpp.obj -c D:\CS120\Project6\main.cpp

CMakeFiles/Project6.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Project6.dir/main.cpp.i"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\CS120\Project6\main.cpp > CMakeFiles\Project6.dir\main.cpp.i

CMakeFiles/Project6.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Project6.dir/main.cpp.s"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\CS120\Project6\main.cpp -o CMakeFiles\Project6.dir\main.cpp.s

CMakeFiles/Project6.dir/main.cpp.obj.requires:

.PHONY : CMakeFiles/Project6.dir/main.cpp.obj.requires

CMakeFiles/Project6.dir/main.cpp.obj.provides: CMakeFiles/Project6.dir/main.cpp.obj.requires
	$(MAKE) -f CMakeFiles\Project6.dir\build.make CMakeFiles/Project6.dir/main.cpp.obj.provides.build
.PHONY : CMakeFiles/Project6.dir/main.cpp.obj.provides

CMakeFiles/Project6.dir/main.cpp.obj.provides.build: CMakeFiles/Project6.dir/main.cpp.obj


CMakeFiles/Project6.dir/Student.cpp.obj: CMakeFiles/Project6.dir/flags.make
CMakeFiles/Project6.dir/Student.cpp.obj: ../Student.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\CS120\Project6\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/Project6.dir/Student.cpp.obj"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Project6.dir\Student.cpp.obj -c D:\CS120\Project6\Student.cpp

CMakeFiles/Project6.dir/Student.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Project6.dir/Student.cpp.i"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\CS120\Project6\Student.cpp > CMakeFiles\Project6.dir\Student.cpp.i

CMakeFiles/Project6.dir/Student.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Project6.dir/Student.cpp.s"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\CS120\Project6\Student.cpp -o CMakeFiles\Project6.dir\Student.cpp.s

CMakeFiles/Project6.dir/Student.cpp.obj.requires:

.PHONY : CMakeFiles/Project6.dir/Student.cpp.obj.requires

CMakeFiles/Project6.dir/Student.cpp.obj.provides: CMakeFiles/Project6.dir/Student.cpp.obj.requires
	$(MAKE) -f CMakeFiles\Project6.dir\build.make CMakeFiles/Project6.dir/Student.cpp.obj.provides.build
.PHONY : CMakeFiles/Project6.dir/Student.cpp.obj.provides

CMakeFiles/Project6.dir/Student.cpp.obj.provides.build: CMakeFiles/Project6.dir/Student.cpp.obj


# Object files for target Project6
Project6_OBJECTS = \
"CMakeFiles/Project6.dir/main.cpp.obj" \
"CMakeFiles/Project6.dir/Student.cpp.obj"

# External object files for target Project6
Project6_EXTERNAL_OBJECTS =

Project6.exe: CMakeFiles/Project6.dir/main.cpp.obj
Project6.exe: CMakeFiles/Project6.dir/Student.cpp.obj
Project6.exe: CMakeFiles/Project6.dir/build.make
Project6.exe: CMakeFiles/Project6.dir/linklibs.rsp
Project6.exe: CMakeFiles/Project6.dir/objects1.rsp
Project6.exe: CMakeFiles/Project6.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=D:\CS120\Project6\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable Project6.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Project6.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Project6.dir/build: Project6.exe

.PHONY : CMakeFiles/Project6.dir/build

CMakeFiles/Project6.dir/requires: CMakeFiles/Project6.dir/main.cpp.obj.requires
CMakeFiles/Project6.dir/requires: CMakeFiles/Project6.dir/Student.cpp.obj.requires

.PHONY : CMakeFiles/Project6.dir/requires

CMakeFiles/Project6.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Project6.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Project6.dir/clean

CMakeFiles/Project6.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" D:\CS120\Project6 D:\CS120\Project6 D:\CS120\Project6\cmake-build-debug D:\CS120\Project6\cmake-build-debug D:\CS120\Project6\cmake-build-debug\CMakeFiles\Project6.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Project6.dir/depend

