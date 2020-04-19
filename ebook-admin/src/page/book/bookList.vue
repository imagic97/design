<template>
  <div>
    <div class="book_list">
      <div class="info_container" v-show="showInfo">
        <span @click="close()" class="el-icon-close"></span>
        <book-info :bookForm="selectBook" />
        <el-button class="btn_change" @click="changeInfo(selectBook)" type="warning" size="small">修改</el-button>
      </div>

      <el-table
        :border="true"
        :data="bookList"
        @row-dblclick="onRowClick"
        ref="bookTable"
        stripe
        style="width: 100%; height: 100%;"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column fixed prop="bookId" label="ID"></el-table-column>
        <el-table-column prop="fileName" label="文件名"></el-table-column>
        <el-table-column prop="title" label="书名"></el-table-column>
        <el-table-column prop="userId" label="上传者"></el-table-column>
        <el-table-column prop="Introduction" label="简介"></el-table-column>
        <el-table-column prop="language" label="语言"></el-table-column>
        <el-table-column prop="publisher" label="出版社"></el-table-column>
        <el-table-column prop="creator" label="作者"></el-table-column>
        <el-table-column prop="isbn" label="ISBN"></el-table-column>
        <el-table-column prop="publishDate" label="出版日期"></el-table-column>
        <el-table-column prop="bookCategory" label="类别"></el-table-column>
        <el-table-column prop="isShare" label="是否公开"></el-table-column>
      </el-table>
    </div>

    <div class="controller">
      <div class="content">
        <div class="operater">
          <el-button @click.stop="handleClick()" type="primary" size="small">查看</el-button>
          <el-button type="info" size="small">冻结</el-button>
          <el-button @click.stop="deleteConfirm()" type="danger" size="small">删除</el-button>
        </div>
        <div class="pagination">
          <el-pagination
            background
            layout="prev,pager, next, jumper, sizes"
            :total="bookTotal"
            :page-size="page.size"
            :page-sizes="[10, 20, 30, 40]"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          ></el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getBookInfoList, getBookTotal, deleteBook } from "@/api";
import bookInfo from "@/components/bookInfo";

export default {
  components: {
    bookInfo
  },
  data() {
    return {
      showInfo: false,
      selectBook: {},
      //  {bookId: "",fileName: "",title: "",Introduction: "",language: "",publisher: "",creator: "",isbn: "",publishDate: ""}
      bookList: [],
      page: {
        size: 10,
        currentPage: 1
      },
      bookTotal: 0
    };
  },
  mounted() {
    this.getBookInfoList(this.page.size, 0);
    getBookTotal().then(Response => {
      if (Response.data.code == 200) {
        this.bookTotal = Response.data.result;
      }
    });
  },
  methods: {
    changeInfo(bookInfo) {
      console.log(bookInfo);
    },
    close() {
      this.showInfo = false;
    },
    onRowClick(row) {
      this.selectBook = row;
      this.showInfo = true;
    },

    deleteConfirm() {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.deleteBooks();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作"
          });
        });
    },
    //删除书籍，操作完成后刷新列表
    async deleteBooks() {
      let bookArray = this.$refs.bookTable.selection;
      let deleteNum = bookArray.length;
      let listNum = this.bookList.length;
      if (deleteNum > listNum) {
        this.$message.error("当前操作错误");
        this.$refs.bookTable.selection = [];
        return;
      }
      for (let i = 0; i < deleteNum; i++) {
        const res = await deleteBook(
          bookArray[i].bookId,
          bookArray[i].fileName
        );
        if (res.data.code == 200) {
          this.$message.success(bookArray[i].title + "被删除");
        }
      }

      this.bookTotal = this.bookTotal - deleteNum;
      //当前页全部删除且页码对于 1，获取前一页面
      if (deleteNum == listNum && this.page.currentPage > 1) {
        this.page.currentPage = this.page.currentPage - 1;
        this.getBookInfoList(
          this.page.size,
          this.page.size * (this.page.currentPage - 1)
        );
      } else {
        //重新刷新当前页面
        this.getBookInfoList(
          this.page.size,
          this.page.size * (this.page.currentPage - 1)
        );
      }
    },
    handleClick() {
      console.log(this.$refs.bookTable.selection);
    },
    getBookInfoList(num, offset) {
      getBookInfoList(num, offset).then(Response => {
        if (Response.data.code == 200) {
          this.bookList = Response.data.result;
        }
      });
    },
    handleCurrentChange(cPage) {
      this.page.currentPage = cPage;
      this.getBookInfoList(this.page.size, this.page.size * (cPage - 1));
    },
    handleSizeChange(pageSize) {
      this.getBookInfoList(
        pageSize,
        this.page.size * (this.page.currentPage - 1)
      );
      this.page.size = pageSize;
    }
  }
};
</script>
<style lang="less" scoped>
@import "../../assets/style/mixin";
.book_list {
  margin-bottom: 58px;
}
.controller {
  width: 100%;
  padding: 12px 0 0 16px;
  position: absolute;
  bottom: 0;
  background-color: #eff2f7;
  z-index: 199;
}
.info_container {
  .ctt;
  padding: 0 36px 48px 36px;
  z-index: 199;
  background-color: #eff2f7;

  span {
    position: absolute;
    right: 12px;
    margin: 15px 10px;
    font-size: 24px;
    &:hover {
      color: red;
      transform: rotateZ(120deg);
    }
  }

  .btn_change {
    .lr;
  }
}
</style>
