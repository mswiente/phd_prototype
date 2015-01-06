phd_prototype
=============

Source code of research prototypes

The code is organized as follows:

### BatchProcessor
Contains the implementation of the batch prototype based on Spring Batch.

### BillingRouter
Contains the implementation of the messaging prototype based on Apache Camel.

### CamelUtils
Contains common Apache Camel specific logging utilities

### DataGenerator
Contains the load generator.

### FunctionalCore
Contains the common business functionality of the system used by batch and messaging prototypes.

### MediationBatchRoute
Contains the wrapper to integrate the mediation processor of the batch prototype with Apache Camel.

### MediationService
Contains the webservice wrapper of mediation processor used by the messaging prototpye.

### PerformanceMonitor
Contains the Feedback-Control framework to monitor, measure and control the messaging system.

### RatingBatchRoute
Contains the wrapper to integrate the rating processor of the batch prototype with Apache Camel.

### RatingService
Contains the webservice wrapper of the rating processor used by the messaging system.
