cmake_minimum_required(VERSION 3.8)
project(dji_rc_pro_bridge)

if(CMAKE_COMPILER_IS_GNUCXX OR CMAKE_CXX_COMPILER_ID MATCHES "Clang")
  add_compile_options(-Wall -Wextra -Wpedantic)
endif()

find_package(ament_cmake REQUIRED)
find_package(geometry_msgs REQUIRED)
find_package(OpenSSL REQUIRED)
find_package(rclcpp REQUIRED)
find_package(rosidl_default_generators REQUIRED)
find_package(std_msgs REQUIRED)

rosidl_generate_interfaces(${PROJECT_NAME}
  "msg/ChassisCtrl.msg"
)

include_directories(include)

add_library(${PROJECT_NAME}_proto SHARED
  src/protocol/protocol_parser.cpp
)

ament_target_dependencies(${PROJECT_NAME}_proto
  rclcpp
  std_msgs
)

rosidl_get_typesupport_target(cpp_typesupport_target ${PROJECT_NAME} "rosidl_typesupport_cpp")

target_link_libraries(${PROJECT_NAME}_proto "${cpp_typesupport_target}")

add_executable(dji_rc_pro_gateway src/tools/dji_rc_pro_gateway.cpp)
target_link_libraries(dji_rc_pro_gateway ${PROJECT_NAME}_proto "${cpp_typesupport_target}" OpenSSL::Crypto)
ament_target_dependencies(dji_rc_pro_gateway
  geometry_msgs
  rclcpp
  std_msgs
)

install(TARGETS ${PROJECT_NAME}_proto
  ARCHIVE DESTINATION lib
  LIBRARY DESTINATION lib
  RUNTIME DESTINATION bin
)

install(TARGETS dji_rc_pro_gateway
  DESTINATION lib/${PROJECT_NAME}
)

install(PROGRAMS
  src/tools/dji_rc_pro_ble_gateway.py
  DESTINATION lib/${PROJECT_NAME}
)

install(DIRECTORY include/
  DESTINATION include
)

install(DIRECTORY launch
  DESTINATION share/${PROJECT_NAME}
)

if(BUILD_TESTING)
  enable_testing()
  add_executable(test_client_address_snapshot
    test/test_client_address_snapshot.cpp
  )
  target_include_directories(test_client_address_snapshot PRIVATE include)
  add_test(NAME test_client_address_snapshot COMMAND test_client_address_snapshot)
endif()

ament_export_include_directories(include)
ament_export_libraries(${PROJECT_NAME}_proto)
ament_export_dependencies(rosidl_default_runtime)
ament_package()
