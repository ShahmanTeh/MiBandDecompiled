.class Lcom/tencent/open/A;
.super Ljava/lang/Object;


# instance fields
.field a:I

.field b:Ljava/lang/String;

.field c:Ljava/lang/String;

.field d:J

.field e:I


# direct methods
.method public constructor <init>(ILjava/lang/String;Ljava/lang/String;JI)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/tencent/open/A;->a:I

    iput-object p2, p0, Lcom/tencent/open/A;->b:Ljava/lang/String;

    iput-object p3, p0, Lcom/tencent/open/A;->c:Ljava/lang/String;

    iput-wide p4, p0, Lcom/tencent/open/A;->d:J

    iput p6, p0, Lcom/tencent/open/A;->e:I

    return-void
.end method
