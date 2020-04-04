<template>
  <div class="category_view">
    <div class="category_header">
      <h1>书城</h1>
    </div>
    <div class="category_conteain">
      <div class="category_left">
        <ul class="category_list">
          <li class="category total" @click="getBook(1)">分类</li>
          <li
            class="category"
            v-for="(item,key2) in categoryList"
            :key="key2"
            @click="getBook(item.categoryId)"
          >
            <span>{{item.categoryName}}</span>
          </li>
        </ul>
      </div>
      <div class="catagory_book">
        <div class="result">
          <search-result :searchResult="result" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCategory, getCategoryBook } from "@/api/api";
import searchResult from "@/components/search";
// import VE from "@/util/vueEvent";
export default {
  components: {
    searchResult
  },
  data() {
    return {
      result: [],
      categoryList: []
    };
  },
  mounted() {
    this.init();
    this.getBook(1);
  },
  methods: {
    init() {
      getCategory().then(Response => {
        if (Response.data.code == 200) {
          this.categoryList = Response.data.result;
        }
      });
    },
    getBook(categoryId) {
      getCategoryBook(categoryId).then(Response => {
        if (Response.data.code == 200) {
          this.result = Response.data.result;
        }
      });
    }
  }
};
</script>

<style scoped>
.category_view {
  margin: 0 0 72px 0;
}
.category_header {
  height: 64px;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 25, 104, 0.3);
}

h1 {
  text-align: center;
  font-size: 24px;
  line-height: 64px;
}
.category_left {
  width: 126px;
  height: 100%;
  flex: 0 0 126px;
  box-shadow: 0 1px 3px rgba(0, 25, 104, 0.3);
}
.category_conteain {
  display: flex;
  height: 100%;
}
.category {
  border-radius: 0;
  border: solid #c6c7ca;
  border-width: 0 0 1px;
  cursor: pointer;
  height: 64px;
  line-height: 64px;
  text-align: center;
}
.catagory_book {
  flex: 1;
  width: 0;
}
@media (max-width: 460px) {
  .category_conteain {
    flex-direction: column;
  }
  .catagory_book {
    flex: 1;
    /* width: 100%; */
  }
  .category_list {
    display: inline-block;
    overflow: scroll;
    width: auto;
  }
  .category {
    margin: 5px 12px;
    display: inline;
    border-radius: 0;
    border: solid #c6c7ca;
    border-width: 0 0 1px;
    line-height: 36px;
  }
  .category_left {
    flex: 0;
    width: auto;
    height: 36px;
  }
  .catagory_book {
    flex: 1;
    width: 100%;
  }
}
</style>