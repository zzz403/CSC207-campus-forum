# 项目结构手册

## 目录结构

- ### `app`
    - #### `Factory`
        - 用于初始化Service和各个面板的工厂类。
    - #### `Main`
        - 用于启动项目的主类。
    - #### 其他
        - 例如`H2Setup`等需要单独运行的类。

- ### `cache`
    - 包含各种缓存接口及其实现类，用于缓存不同实体的数据以提高访问效率。

- ### `config`
    - #### `DatabaseConfig`
        - 包含数据库连接配置的类，如修改数据库连接地址等信息。

- ### `data_access`
    - #### `DAI`
        - 数据访问接口，定义了与数据库交互的行为。
    - #### `DAO`
        - 数据访问对象，包含与数据库具体交互的实现。

- ### `entity`
    - 包含不同实体类及其相关类，例如`Board`、`ChatGroup`、`ChatMessage`、`Comment`、`GroupMember`、`Post`和`User`等。

- ### `interface_adapter`
    - #### `common`
        - 通用视图模型，包括`ViewManagerModel`。
    - #### 不同界面的interface adapter
        - 包括：
            - `Controller`：处理用户交互的控制器。
            - `Presenter`：展示结果的展示器。
            - `State`：表示状态的类。
            - `ViewModel`：视图模型，管理界面的数据和状态。

- ### `service`
    - #### `Service`
        - 服务接口定义，包含对各实体的服务操作。
    - #### `ServiceImpl`
        - 服务接口的实现类，提供具体的服务操作实现。

- ### `session`
    - #### `SessionManager`
        - 用于管理当前会话的类。

- ### `use_case`
    - #### 不同画面的用例
        - 用例包括：
            - `InputBoundary`：定义用例的输入接口。
            - `InputData`：封装输入数据的类。
            - `Interactor`：交互器，实现逻辑。
            - `OutputBoundary`：定义用例的输出接口。
            - `OutputData`：封装输出数据的类。

- ### `view`
    - 包含各个视图类，用于界面显示和交互。
    - #### `ViewManager`
        - 视图管理类，管理不同视图之间的切换。

## 组件关系及关联

### `InputBoundary`
- 定义用例的输入接口。
- 关联：由`Interactor`实现，用于接收`Controller`传递的输入数据。

### `InputData`
- 封装输入数据的类。
- 关联：由`Controller`创建，并通过`InputBoundary`传递给`Interactor`。

### `Interactor`
- 交互器，实现具体的业务逻辑。
- 关联：
    - 实现`InputBoundary`接口。
    - 使用`InputData`进行业务处理。
    - 调用`OutputBoundary`返回处理结果。

### `OutputBoundary`
- 定义用例的输出接口。
- 关联：由`Presenter`实现，用于接收`Interactor`的处理结果并准备视图更新数据。

### `OutputData`
- 封装输出数据的类。
- 关联：由`Interactor`创建，并通过`OutputBoundary`传递给`Presenter`。

### `Controller`
- 处理用户交互的控制器。
- 关联：
    - 调用`InputBoundary`接口将用户输入传递给`Interactor`。
    - 接收`Interactor`通过`OutputBoundary`传递的处理结果。

### `Presenter`
- 展示结果的展示器。
- 关联：
    - 实现`OutputBoundary`接口。
    - 准备视图更新所需的数据，并通过`ViewModel`更新视图。

### `State`
- 表示状态的类。
- 关联：由`ViewModel`管理，用于表示视图的当前状态和数据。

### `ViewModel`
- 视图模型，管理界面的数据和状态。
- 关联：
    - 包含`State`实例。
    - 监听`Presenter`更新的数据变化，并通知视图进行更新。

### `ViewManagerModel`
- 管理不同视图之间的切换。
- 关联：
    - 包含视图切换逻辑。
    - 由`ViewManager`使用，以实现视图切换功能。

## 我们如何创建一个新的功能（画面）？

1. **创建一个新的entity**
    - **Factory**
        1. 编写一个Factory接口。
        2. 编写该接口的实现类，实现`create`方法来创建一个新的实体。

2. **创建一个新的页面**
    - **UseCaseFactory**
        1. 在`app`包中创建一个新的`UseCaseFactory`类。
        2. 在该工厂类中生成所需的所有控制器，包括：
            - `Presenter`
            - `Interactor`
        3. 将创建的视图实例返回。

3. **获取数据**
    - **ServiceFactory**
        1. 在`ServiceFactory`的`initialize()`方法中创建一个对应数据库的服务。
        2. 在创建页面时，将该服务注入。例如：
            ```java
            UserService userService = ServiceFactory.getUserService();
            ```

4. **State的定义和使用**
    - **State**
        - 包含视图中所有的变量信息。
    - **使用方法**
        1. 在视图模型中定义`State`类。
        2. 在视图模型中添加属性更改监听器，以响应状态的变化。

5. **如何监听State的变化来切换View呢？**
    ```java
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    ```
    然后在view中
    ```java
        loginViewModel.addPropertyChangeListener(evt -> {
        if ("state".equals(evt.getPropertyName())) {
            LoginState state = loginViewModel.getState();
            usernameField.setText(state.getUsername());
            passwordField.setText(state.getPassword());
            usernameErrorLabel.setText(state.getUsernameError());
            passwordErrorLabel.setText(state.getPasswordError());
        }
        });
    ```
    同样你可以修改"state" 来传输不一样的指令