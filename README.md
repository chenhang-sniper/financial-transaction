# 金融交易管理系统

## 一、系统介绍
本系统是一个基于 Java 21 和 Spring Boot 构建的金融交易管理系统，旨在为用户提供一个简单易用的平台，用于记录、查看和管理金融交易记录。与传统系统不同，本系统将交易数据存储在内存中，不依赖于持久存储，这使得系统更加轻量级且易于部署。

---

## 二、技术工具

- **后端框架**：Spring Boot，结合 Swagger 用于接口文档管理和测试，提供清晰的 API 文档，便于前端开发者与后端服务的对接。
- **前端框架**：Vue3 配合 NodeJS，利用 Vue3 的响应式系统和组件化开发模式，结合 Element-Plus 提供的丰富 UI 组件库，打造现代化且用户友好的界面。
- **缓存技术**：Spring Cache，通过缓存机制提升读取操作的效率，优化用户体验，减少对内存数据库的访问压力。
- **部署工具**：Docker，使用容器化技术简化部署过程。

---

## 三、系统架构

系统采用分层架构设计，前后端分离，每个部分都有明确的职责和功能：

- **前端模块**：
    - 使用 Vue3 和 NodeJS 进行开发，构建动态、响应式的用户界面。
    - 通过 Vuex 进行状态管理，实现组件之间的数据共享和通信。
    - 使用 Element-Plus 提供的高质量 UI 组件库来构建现代化的页面，提升用户体验。

- **后端模块**：
    - 基于 SpringMVC 框架，明确划分控制层、服务层和数据访问层。
    - 数据访问层采用抽象 BaseDao 设计，利用动态代理模式代理内存数据库，实现数据源的灵活切换。
    - 在数据服务层，利用 Spring Cache 缓存查询结果和列表数据，减少重复计算和数据访问。
    - 控制层遵循 RESTful API 设计原则，通过 Swagger 自动生成接口文档，方便前端调试和集成。

---

## 四、整体设计思路

### 4.1 数据访问层
1. 实现抽象 BaseDao，采用动态代理模式代理内存数据库 MemoryMapper，支持无缝切换数据源。
2. 使用 ConcurrentHashMap 作为底层数据存储，确保线程安全，以实体主键作为 key，提升数据读写速度。
3. 对存储实体进行封装，添加时间戳作为版本号，主键采用雪花算法生成，保证主键的唯一性和单调递增。

### 4.2 数据服务层
1. 结合 Spring Cache 进行缓存管理，当系统无更新、插入操作时，查询和列表查找均从缓存中读取。
2. 实现交易错误异常处理机制，通过封装枚举错误类型，全局捕捉并返回友好提示信息，例如创建重复事务或删除不存在的交易。
3. 并发控制采用读写锁，确保更新、插入、删除等操作的线程安全。

### 4.3 控制层
1. 遵循 RESTful API 设计原则，使用 Swagger 管理接口，自动生成清晰、结构良好的 API 文档。
2. 增加参数后端校验 TransactionValidator，对请求参数进行严格的校验，返回详细的错误信息。

---

## 五、系统界面

### 5.1 Swagger 管理接口
![](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/images/swagger-ui.png)

### 5.2 交易列表页面
![](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/images/web-ui-add.png)


## 六、部署步骤

### 1. 准备环境

确保已安装以下软件：

- **Docker**：用于容器化应用
- **Docker Compose**：用于定义和运行多容器应用

可以通过以下命令检查是否已正确安装：

```bash
docker --version
docker-compose --version
```

### 2. 构建镜像
在项目根目录下运行以下命令来构建前端和后端镜像：
```bash
docker-compose build
```
### 3. 启动服务
启动前端和后端服务：
```bash
docker-compose up -d
```
### 4. 验证部署
- 查看后端 API 文档
   访问 http://8.138.192.95:8080/swagger-ui/index.html
- 查看前端应用
   访问 http://8.138.192.95

### 5. 停止和删除服务
停止服务：
```bash
docker-compose stop
```
删除服务和镜像：
```bash
docker-compose down
```

### 6.第三方库
- Spring Boot Starter Web
  - 用于构建 Web 应用程序，提供嵌入式 Tomcat 服务器和 Spring MVC 支持，简化 Web 开发流程。
- Spring Boot Starter
  - 提供 Spring Boot 应用的基本配置和依赖项，是构建 Spring Boot 应用的核心依赖。
-  Spring Boot Starter Validation
  - 提供数据验证功能，支持使用 JSR-380 规范对数据进行校验，确保输入数据的合法性。
- SpringDoc OpenAPI Starter Webmvc UI
  - 用于生成 API 文档和交互式界面，便于开发者查看和测试 API，符合 OpenAPI 标准。
- Jackson Databind
  - 处理 JSON 数据的序列化和反序列化，方便 JSON 格式数据的转换和操作，支持对象与 JSON 的双向转换。
- Lombok
  - 通过注解简化代码编写，减少样板代码，如自动生成 getter/setter 方法、构造函数、toString 方法等。
- Spring Context
  - 提供 Spring 框架的核心功能，包括依赖注入、事件发布/订阅等，是 Spring 应用的基础模块。
- JUnit Jupiter API
  - 提供 JUnit 5 测试框架的 API，用于编写和运行单元测试，支持更灵活的测试语法和注解。
- JUnit Jupiter Engine
  - 提供 JUnit 5 测试框架的运行引擎，用于执行单元测试，支持测试的发现和运行。
- Spring Boot Starter Cache
  - 提供缓存支持，方便与各种缓存实现（如 EhCache、Redis 等）进行集成，提高应用性能。
- Mockito Core
  - 提供模拟对象功能，用于在单元测试中模拟依赖项的行为，便于编写隔离的单元测试。

## 七、源代码相关描述

### 1. 并发控制和缓存加速

```java
@Service
public class TransactionServiceImpl implements TransactionService {
  @Autowired
  private TransactionDao transactionDao;
  //读写锁
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  @Cacheable(value = "transactions", key = "#accountId + #transactionType + #transactionStatus + #transactionMethod + #starTime + #endTime + #pageNo + #pageSize")
  public PageListingResult<Transaction> listPaging(String accountId, TransactionType transactionType, TransactionStatus transactionStatus, TransactionMethod transactionMethod,
                                                   LocalDateTime starTime, LocalDateTime endTime,
                                                   Integer pageNo, Integer pageSize) {
    lock.readLock().lock();
    try {
      List<Transaction> transactionList = transactionDao.queryList(accountId, transactionType,
              transactionStatus, transactionMethod, starTime, endTime);
      if (!CollectionUtils.isEmpty(transactionList)) {
        int start = (pageNo - 1) * pageSize;
        int end = Math.min(start + pageSize, transactionList.size());
        List<Transaction> list = transactionList.subList(start, end);
        PageListingResult rs = PageListingResult.<Transaction>builder()
                .totalCount(transactionList.size()).currentPage(pageNo).pageSize(pageSize).records(list)
                .build();
        return rs;
      }
      return null;
    } finally {
      lock.readLock().unlock();
    }
  }
}
```

### 2. 可扩展性和接口健壮性设计

```java
/** 全局异常捕捉 **/
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public Result<Object> handleServiceException(ServiceException e, HandlerMethod handlerMethod) {
    log.error("{} Meet a ServiceException: {}", handlerMethod.getShortLogMessage(), e.getMessage());
    return new Result<>(e.getCode(), e.getMessage());
  }
}
/** 所有错误异常枚举 **/
public enum Status {
  SUCCESS(0, "success", "成功"),
  INTERNAL_SERVER_ERROR(1000, "Internal Server Error: {0}", "服务端异常: {0}"),
  REQUEST_PARAMS_NOT_VALID_ERROR(1001, "request parameter {0} is not valid", "请求参数无效[{0}]"),
  TRANSACTION_NOT_EXIST(1002, "Transaction [{0}] not exists", "交易流水不存在:{0}"),
  TRANSACTION_ALREADY_EXISTS(1003, "Transaction already exists: {0}", "交易流水重复提交: {0}"),
  CREATE_TRANSACTION_ERROR(1004, "Create transaction error", "创建交易失败"),
  UPDATE_TRANSACTION_ERROR(1005, "Update transaction error", "更新交易失败"),
  DELETE_TRANSACTION_ERROR(1006, "Delete transaction error", "删除交易失败"),
  FIND_TRANSACTION_ERROR(1007, "Find transaction error", "查找交易失败"),
  FIND_TRANSACTION_LIST_ERROR(1008, "Find transaction list error", "查找交易列表失败");
}

/** 参数校验 **/
public class TransactionValidator {
    public static void validateTransactionRequest(TransactionCreateRequest request) {
        // Check if the transaction type is empty
        if (request.getTransactionType() == null) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "Transaction type cannot be empty.");
        }
        // Check if the transaction amount is greater than zero
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR,"Transaction amount must be greater than zero.");
        }
        // Check if the transaction account ID is null
        if (request.getAccountId() == null) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "Transaction account cannot be null.");
        }
    }
}
```

### 3. 内存Mapper

```java
public class MemoryMapper<Entity extends MemoryEntity> {
    // ID生成器（线程安全）
    private final AtomicLong idGenerator = new AtomicLong(0);

    // 索引存储：按ID快速查找（线程安全）
    private final ConcurrentMap<Long, Entity> indexMapping = new ConcurrentHashMap<>();

    /**
     * 保存一个实体如果实体的ID为空，则生成一个新的ID
     * 并将当前时间戳设置为实体的时间戳
     *
     * @param model 要保存的实体
     * @return 保存后的实体
     */
    public Entity save(Entity model) {
        if (model.getId() == null) {
            //自增
            model.setId(idGenerator.incrementAndGet());
        }
        model.setTimestamp(LocalDateTime.now());
        indexMapping.put(model.getId(), model);
        return model;
    }
```

## 八、测试
### 1. 单元测试
  -  使用使用Maven Surefire Report Plugin执行测试，并生成单元测试报告。在Maven中，可以通过以下命令来执行：
   ```bash
    mvn  surefire-report:report
   ```
以下是单元测试报告的链接：[测试报告](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/report/surefire-report.html)
  -  使用JaCoCo插件执行测试，并生成覆盖率报告。在Maven中，可以通过以下命令来执行：
   ```bash
    mvn  test
   ```
以下是覆盖率报告的链接：[覆盖率报告](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/images/test-coverage.png)
### 2. 压力测试
  -  使用JMeter进行压力测试，编写[测试样例数据](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/report/sample_data.csv)1000条，安装Random CSV Data插件，编写测试脚本，并运行测试。
  -  使用10000个线程1秒钟并发请求，由于是内存存储实体，所以速度还是挺快的，吞吐量基本上达到单个tomcat的性能峰值。测试结果截图示例如下：
  -  ![](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/iamge/pressure-test.png)
 
## 九、接口文档
  -   [接口文档](https://github.com/chenhang-sniper/financial-transaction/blob/main/docs/api.json)

## 十、扩展
1. 数据同步优化
- 当多个线程同时更新同一实体时，会出现数据互相直接覆盖的情况，建议使用版本号来实现友好更新.
- 在更新操作中，先检查本地实体的版本号与 ConcurrentMap 中最新实体的版本号是否一致。若一致，则执行更新操作；
- 若不一致，则说明有其他线程已更新了该实体，此时应提示用户数据已发生变更，需重新获取最新数据后再次尝试更新。
2. 缓存优化
- 目前系统采用单机内存存储数据实体，仅使用 Spring Cache 进行管理，相比引入外部分布式缓存中间件, 能以极高的读写速度满足应用需求, 若是分布式可以考虑分布式缓存。


 
